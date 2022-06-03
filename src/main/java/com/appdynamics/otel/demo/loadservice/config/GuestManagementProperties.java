package com.appdynamics.otel.demo.loadservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("services.guestmanagement")
public class GuestManagementProperties {
    private String host;
    private String port;
}
