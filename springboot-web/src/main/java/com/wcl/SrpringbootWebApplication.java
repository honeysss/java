package com.wcl;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
@MapperScan(basePackages = "com.wcl.mapper.*")
@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class})
@EnableCaching
public class SrpringbootWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrpringbootWebApplication.class, args);
	}

}
