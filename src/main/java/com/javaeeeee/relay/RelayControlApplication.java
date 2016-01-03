package com.javaeeeee.relay;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.javaeeeee.relay.resources.RelayControlResource;
import com.javaeeeee.relay.health.TemplateHealthCheck;

public class RelayControlApplication extends Application<RelayControlConfiguration> {
    public static void main(String[] args) throws Exception {
        new RelayControlApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<RelayControlConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(RelayControlConfiguration configuration,
                    Environment environment) {
        final RelayControlResource resource = new RelayControlResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
                environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }


}

