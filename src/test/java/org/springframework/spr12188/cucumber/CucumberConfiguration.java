package org.springframework.spr12188.cucumber;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Configuration
public class CucumberConfiguration {

    @Bean
    public static WebTarget client() {

        final Client client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class).build();

        return client.target("http://localhost:8080/spr-12188");
    }
}
