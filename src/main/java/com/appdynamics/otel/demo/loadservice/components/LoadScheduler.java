package com.appdynamics.otel.demo.loadservice.components;

import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.appdynamics.otel.demo.loadservice.config.GuestManagementProperties;
import com.appdynamics.otel.demo.loadservice.entities.Guest;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoadScheduler {

        private final RestTemplate restTemplate = new RestTemplate();
        private final GuestManagementProperties guestManagementProperties;

        private static String GUEST_MANAGEMENT_URL = "http://%s:%s/api/%s";

        @Scheduled(fixedRate = 10000)
        public void createGuest() throws RestClientException, JsonProcessingException {

                Guest guest = new Guest(UUID.randomUUID().toString() + "@nomail.com", "first", "last", "110519906",
                                "19/03/1999");

                String endpoint = String.format(GUEST_MANAGEMENT_URL, guestManagementProperties.getHost(),
                                guestManagementProperties.getPort(), "Guest");

                restTemplate.postForObject(endpoint,
                                guest, Guest.class);
                log.info("created guest: {}", guest);
        }

}
