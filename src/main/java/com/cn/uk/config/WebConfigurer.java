package com.cn.uk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "sessionUser";

    @Bean
    public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {
        return new AccessTokenVerifyInterceptor();
    }

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public MobileInterceptor getMobileInterceptor(){
        return new MobileInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration tokenInterceptor = registry.addInterceptor(tokenVerifyInterceptor());
        /* InterceptorRegistration mobileInterceptor = registry.addInterceptor(getMobileInterceptor());*/


        // 排除配置
        /*securityInterceptor.excludePathPatterns("/error");
        securityInterceptor.excludePathPatterns("/login**");
        securityInterceptor.excludePathPatterns("/autoLogin");
        securityInterceptor.excludePathPatterns("/api/**");
        securityInterceptor.excludePathPatterns("/m/**");
        securityInterceptor.excludePathPatterns("/m");

        mobileInterceptor.excludePathPatterns("/m/login**");
        mobileInterceptor.excludePathPatterns("/m/wxError");*/

        tokenInterceptor.excludePathPatterns("/butler/sso/login");
        tokenInterceptor.excludePathPatterns("/alarm_images/**");
        tokenInterceptor.excludePathPatterns("/images/**");
        tokenInterceptor.excludePathPatterns("/videos/**");
        tokenInterceptor.excludePathPatterns("/show**");
        tokenInterceptor.excludePathPatterns("/js**");
        tokenInterceptor.excludePathPatterns("/data/**");
      //  securityInterceptor.excludePathPatterns("/m/login**");
        /*// 排除swagger2
        securityInterceptor.excludePathPatterns("/configuration/ui");
        securityInterceptor.excludePathPatterns("/swagger-resources/**");
        securityInterceptor.excludePathPatterns("/configuration/security");
        securityInterceptor.excludePathPatterns("/swagger-ui.html");
        securityInterceptor.excludePathPatterns("/webjars/**");

        // 排除静态资源
        securityInterceptor.excludePathPatterns("/js/**");
        securityInterceptor.excludePathPatterns("/css/**");
        securityInterceptor.excludePathPatterns("/fontawesome/**");
        securityInterceptor.excludePathPatterns("/F/**");
        securityInterceptor.excludePathPatterns("/lib/**");
        securityInterceptor.excludePathPatterns("/res/**");
        securityInterceptor.excludePathPatterns("/mui/**");

        mobileInterceptor.excludePathPatterns("/mui/**");*/
        
        // 拦截配置
        tokenInterceptor.addPathPatterns("/message/messageSever/getMessageHistorys");
        /*securityInterceptor.addPathPatterns("/**");
        mobileInterceptor.addPathPatterns("/m/**");*/

        // ThymeLeaf
     /*   registry.addInterceptor(new ThymeleafLayoutInterceptor());*/
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
}
