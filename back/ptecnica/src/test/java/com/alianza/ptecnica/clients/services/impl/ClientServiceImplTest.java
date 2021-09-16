package com.alianza.ptecnica.clients.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientServiceImplTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void list() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/clients", String.class)).contains("[]");
    }

    @Test
    void ListBySharedKey() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/clients/search?sharedKeySearch=x", String.class)).contains("[]");
    }
}