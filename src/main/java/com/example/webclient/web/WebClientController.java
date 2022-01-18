package com.example.webclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class WebClientController {
	
    private final Logger log = LoggerFactory.getLogger(WebClientController.class);
    
    // filter로 SCL에서 제공하는 ReactorLoadBalancerExchangeFilterFunction을 지정
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    WebClientController(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.lbFunction = lbFunction;
    }

    @GetMapping("/testsclb1")    
    public Mono<String> testSCLB1() {
        WebClient client = WebClient.builder()
            .filter(this.lbFunction)
            .baseUrl("http://webserver") //baseUrl은 http://{Service ID}로 지정
            .build();

        return client.get()
            .uri("/webclient/test SCLB1")
            .retrieve()
            .bodyToMono(String.class);
    }
    
//    @GetMapping("/testgateway")    
//    public Mono<String> testGateway() {
//        WebClient client = WebClient.builder()
//            .filter(this.lbFunction)
//            .baseUrl("http://scgw") //baseUrl은 http://{Service ID}로 지정
//            .build();
//
//        return client.get()
//            .uri("/webserver/webclient/test SCLB1")
//            .retrieve()
//            .bodyToMono(String.class);
//    }
}
