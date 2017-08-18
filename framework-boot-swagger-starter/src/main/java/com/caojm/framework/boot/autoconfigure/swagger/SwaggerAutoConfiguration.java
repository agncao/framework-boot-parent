package com.caojm.framework.boot.autoconfigure.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Swagger 相关配置
 *
 * @author :曹建明
 * @date :2017-08-17 10:46:13
 */
@Configuration
@EnableSwagger2
@ConditionalOnClass(EnableSwagger2.class)
public class SwaggerAutoConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);

    /**
     * 从哪个包开始扫描
     */
    @Value("${swagger.basePackage:cn.caojm}")
    private String SWAGGER_SCAN_BASE_PACKAGE = "cn.caojm";
    /**
     * API版本
     */
    @Value("${swagger.version:1.0-SNAPSHOT}")
    private String VERSION ;
    @Value("${swagger.title:Rest API 接口}")
    private String title ;
    @Value("${swagger.description:Rest API 接口}")
    private String description ;

    /**
     * 构建ApiInfo对象，这些信息会在swagger ui中进行展示
     * @return the api info
     */
    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title(title);    //api标题
        apiInfoBuilder.description(description);    //api描述
        apiInfoBuilder.license("Terms of service");
        apiInfoBuilder.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0");
        apiInfoBuilder.version(VERSION);    //版本号
        apiInfoBuilder.contact(new Contact("Cao J.M.", "", "910922164@qq.com")); //
        return apiInfoBuilder.build();
    }

    /**
     * 创建api基本信息
     * @return the docket
     */
    @Bean
    @ConditionalOnMissingBean
    public Docket customImplementation() {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info("开始自定义restful api swagger ui内容");
        logger.info("SWAGGER_SCAN_BASE_PACKAGE={},title={},description={}",SWAGGER_SCAN_BASE_PACKAGE,title,description);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .build()
                .directModelSubstitute(LocalDate.class,  java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }
}
