package com.example.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("Spring Cloud Loadbalancer")
				.description("SCLB Test")
				.version("1.0")
				.build();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.webclient.web"))
				.paths(PathSelectors.any())
				.build();		
		
		/*
		 * Docket: Swagger 설정의 핵심이 되는 Bean useDefaultResponseMessages: Swagger 에서 제공해주는
		 * 기본 응답 코드 (200, 401, 403, 404). false 로 설정하면 기본 응답 코드를 노출하지 않음 apis: api 스펙이
		 * 작성되어 있는 패키지 (Controller) 를 지정 paths: apis 에 있는 API 중 특정 path 를 선택
		 * apiInfo:Swagger UI 로 노출할 정보
		 */
	}
}
