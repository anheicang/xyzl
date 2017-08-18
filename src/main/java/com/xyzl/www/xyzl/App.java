package com.xyzl.www.xyzl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.xyzl.www.xyzl.netty.AdminRecordNettyServer;

/**
 * Hello world!
 *
 */
@Controller
@SpringBootApplication
@ServletComponentScan
@ComponentScan
@EnableAsync
@EnableScheduling
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
		AdminRecordNettyServer adminRecordNettyServer = new AdminRecordNettyServer();
		try {
			adminRecordNettyServer.bind(9333);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
