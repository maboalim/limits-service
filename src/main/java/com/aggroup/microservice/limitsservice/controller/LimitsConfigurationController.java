package com.aggroup.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aggroup.microservice.limitsservice.Configuration;
import com.aggroup.microservice.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * This class is used to return the default configuration limit which exists in
 * spring-cloud-config-server It has also test methods for using hystrix and
 * resilience circle breaker
 * 
 * @author Mina
 *
 */
@RestController
public class LimitsConfigurationController {

	@Autowired
	Configuration configuration;

	@GetMapping("limits")
	public LimitConfiguration retrieveLimitsConfiguration() {
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
	}

	/**
	 * Test method for hystrix
	 * 
	 * @return
	 */
	@GetMapping("limits-hystrix-fault-tolerance")
	@HystrixCommand(fallbackMethod = "fallbackRetrieveLimitsConfiguration")
	public LimitConfiguration retrieveLimitsConfigurationHystrixCircleBreaker() {
		// test case to test the failure while calling other microservice
		throw new RuntimeException();
	}

	/**
	 * Test method for resilience
	 * 
	 * @return
	 */
	@GetMapping("limits-resilience-fault-tolerance")
	@CircuitBreaker(name = "retreive-limit-service", fallbackMethod = "fallbackRetrieveLimitsConfiguration")
	public LimitConfiguration retrieveLimitsConfigurationResilienceCircleBreaker() {
		// test case to test the failure while calling other microservice
		throw new RuntimeException();
	}

	/**
	 * This method is called by hystrix circle breaker if the call to other
	 * microservices through the method
	 * retrieveLimitsConfigurationHystrixCircleBreaker fails. It will handle what
	 * should happened in failure cases and in this example, it will return default
	 * values
	 * 
	 * @return
	 */
	public LimitConfiguration fallbackRetrieveLimitsConfiguration() {
		return new LimitConfiguration(4, 444);
	}

	/**
	 * This method is called by resilience4j circuit breaker. resilience4j pass the
	 * exception to the method
	 * 
	 * @param ex
	 * @return
	 */
	public LimitConfiguration fallbackRetrieveLimitsConfiguration(Exception ex) {
		return new LimitConfiguration(5, 555);
	}
}
