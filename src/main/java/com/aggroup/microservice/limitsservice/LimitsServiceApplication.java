package com.aggroup.microservice.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @EnableDiscoveryClient added to use Eureka client to get other micro-services
 *                        information
 * @author Mina
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class LimitsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitsServiceApplication.class, args);
	}

}
