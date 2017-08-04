package com.ebean.bundle;

import com.ebean.config.EbeanConfiguration;
import com.google.common.collect.Sets;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Environment;
import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.MatchingNamingConvention;
import io.ebean.config.ServerConfig;
import org.avaje.datasource.DataSourceConfig;
import org.reflections.Reflections;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.sql.Connection;
import java.util.Set;

/**
 * Created by supreeth.vp on 04/08/17.
 */

public  abstract class EbeanBundle<T extends Configuration> implements ConfiguredBundle<T> {

    EbeanServer ebeanServer;

    @Override
    public void run(T config, Environment environment) throws Exception {

        EbeanConfiguration ebeanConfiguration = getEbeanConfiguration(config);

        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setNamingConvention(new MatchingNamingConvention());
        serverConfig.setName(ebeanConfiguration.getDefaultDataSource());
        serverConfig.setDefaultServer(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriver(ebeanConfiguration.getJdbcDriver());
        dataSourceConfig.setUsername(ebeanConfiguration.getUserName());
        dataSourceConfig.setPassword(ebeanConfiguration.getPassword());
        dataSourceConfig.setUrl(ebeanConfiguration.getJdbcUrl());
        dataSourceConfig.setIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ);

        // using reflection to get all the entity beans and register them
        Reflections reflections = new Reflections(ebeanConfiguration.getModelPackage());
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
        Set<Class<?>> embeddables = reflections.getTypesAnnotatedWith(Embeddable.class);

        Sets.union(embeddables, entities).forEach(serverConfig::addClass);

        serverConfig.setDataSourceConfig(dataSourceConfig);
        ebeanServer = EbeanServerFactory.create(serverConfig);
    }

    public abstract EbeanConfiguration getEbeanConfiguration(T config);
}
