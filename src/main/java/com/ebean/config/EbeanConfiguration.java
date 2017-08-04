package com.ebean.config;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by supreeth.vp on 04/08/17.
 */
@Data
public class EbeanConfiguration {
    @NotNull
    private String defaultDataSource;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private String jdbcUrl;
    @NotNull
    private String jdbcDriver;
    @NotNull
    private String modelPackage;
}

