package com.caojm.framework.boot.autoconfigure.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2017/8/29.
 */
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String version="1.0.0";
    private String basePackage="com.caojm";
    private String title="Rest API";
    private String description="Rest API";

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
