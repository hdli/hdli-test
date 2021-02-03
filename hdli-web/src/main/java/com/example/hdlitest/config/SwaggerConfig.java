package com.example.hdlitest.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author luyi
 * @date 2021/2/3 9:29 下午
 */
@Configuration
@EnableSwagger2
//@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //扫描端口
                .apis(RequestHandlerSelectors.basePackage("com.example.hdlitest.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * name:开发者姓名
     * url:开发者网址
     * email:开发者邮箱
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("张三", "http://www.zhangsan.xyz", "18088888888@163.com");
        return new ApiInfoBuilder()
                .title("测试API接口")//标题
                .description("API接口的描述")//文档接口的描述
                .contact(contact)
                .version("1.1.0")//版本号
                .build();
    }
}
