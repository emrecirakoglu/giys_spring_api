package com.ozgur.giys.api.messaging;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RabbitmqManagementService {

    private RestTemplate restTemplate;
    private HttpHeaders headers;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.exchangeName}")
    private String exchange;

    private String vhost = "%2F";

    public RabbitmqManagementService(
        @Value("${spring.rabbitmq.username}") String host,
        @Value("${spring.rabbitmq.password}") String password
    ){
        this.restTemplate = new RestTemplateBuilder().basicAuthentication(host,password).build();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }

    public Boolean isOnline(String routingKey) {

        String url = "http://{0}:15672/api/bindings/{1}/e/{2}/q/{3}";
        url = MessageFormat.format(url, this.host, this.vhost ,this.exchange, routingKey);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build(true).toUri();

        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<List<QueueInfo>> responseEntity = this.restTemplate.exchange(
                uri,
                HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<QueueInfo>>() {}
                );

        // check response
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return !responseEntity.getBody().isEmpty();
        }
        return false;
    }

    public List<RabbitmqUser> getAllRabbitmqUsers() {

        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);

        String url = "http://{0}:15672/api/users";
        url = MessageFormat.format(url, this.host);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build(true).toUri();

        ResponseEntity<List<RabbitmqUser>> responseEntity = this.restTemplate.exchange(
            uri,
            HttpMethod.GET,
            requestEntity,
            new ParameterizedTypeReference<List<RabbitmqUser>>(){}
            );

        return responseEntity.getBody();

    }

    public ResponseEntity<?> addRabbitmqUser () {

        return null;
    }

}
