package com.bossien.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 * @author Gaojun.Zhou
 * @date 2016年12月29日 下午6:33:43
 */
@EnableWebMvc  
@EnableSwagger2  
@ComponentScan(basePackages = {"com.bossien.controller"})
@Configuration  
public class SwaggerConfig  {

	 @Bean  
	    public Docket createRestApi() {  
	        return new Docket(DocumentationType.SWAGGER_2)  
	                .apiInfo(apiInfo())  
	                .select()  
	                .apis(RequestHandlerSelectors.basePackage("com.bossien.controller"))
	                .paths(PathSelectors.any())  
	                .build();  
	    }  
	  
	    private ApiInfo apiInfo() {  
	        return new ApiInfoBuilder()  
	                .title("培训平台接口文档")
					.description("更多关于接口的问题请详细咨询相关开发人员")
//	                .termsOfServiceUrl("http://1.safety.cc")
	                .contact("博晟云平台在线教育开发组")
	                .version("1.0")  
	                .build();  
	    }  
}
 
