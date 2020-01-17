package com.claro.itec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ItecBackendOpenshiftApplication {


	public static void main(String[] args) { SpringApplication.run(ItecBackendOpenshiftApplication.class, args);
		 }

}
