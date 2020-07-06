package com.cn.uk;

import com.cn.uk.Netty.NettyServer;
import com.cn.uk.taskevents.params.AppStartParam;
import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Hello world!
 *
 */
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableAsync
@MapperScan("com.cn.uk.dao")
public class App extends SpringBootServletInitializer
{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.listeners((ApplicationListener<ApplicationReadyEvent>) evt -> evt.getApplicationContext().publishEvent(new AppStartParam()));
		return application.sources(App.class);
	}

}
