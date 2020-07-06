package com.cn.uk.config;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MybatisSqlInterceptor implements Interceptor {
@Override
public Object intercept(Invocation invocation) throws Throwable {
    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
    Object parameter = null;
    if (invocation.getArgs().length > 1) {
        parameter = invocation.getArgs()[1];
    }

    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
    Configuration configuration = mappedStatement.getConfiguration();

    Object returnValue = null;
    //获取sql语句
    String sql = showSql(configuration, boundSql);
    //获取数据源
    //HikariDataSource db = (HikariDataSource) configuration.getEnvironment().getDataSource();

    String pname = "";
    //sql = sql.replaceAll("\'", "").replace("\"", "");
    // sql+=sql;
    String sp = sql.toLowerCase().replace("call", "");
    if (sp.startsWith("{")) {
        int s = sp.indexOf("{") + 1;
        int e = sp.indexOf("(");
        pname = sp.substring(s, e);
    }

    System.out.println("****************************SQL Executed************************:" + sql);
    //执行结果
    returnValue = invocation.proceed();

    Object parameterObject = boundSql.getParameterObject();


    return returnValue;
}

@Override
public Object plugin(Object target) {
    return Plugin.wrap(target, this);
}

@Override
public void setProperties(Properties properties) {

}

private String getParameterValue(Object obj) {
    String value = null;
    if (obj instanceof String) {
        value = "'" + obj.toString() + "'";
    } else if (obj instanceof Date) {
        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
        value = "'" + formatter.format(obj) + "'";
//            System.out.println(value);
    } else {
        if (obj != null) {
            value = obj.toString();
        } else {
            value = "";
        }

    }
    return value;
}

public String showSql(Configuration configuration, BoundSql boundSql) {
    Object parameterObject = boundSql.getParameterObject();
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
    if (parameterMappings.size() > 0 && parameterObject != null) {
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
    }
    return sql;
}
}
