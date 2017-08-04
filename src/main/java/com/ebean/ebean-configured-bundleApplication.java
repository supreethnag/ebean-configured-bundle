package com.ebean;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ebean-configured-bundleApplication extends Application<ebean-configured-bundleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ebean-configured-bundleApplication().run(args);
    }

    @Override
    public String getName() {
        return "ebean-configured-bundle";
    }

    @Override
    public void initialize(final Bootstrap<ebean-configured-bundleConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ebean-configured-bundleConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
