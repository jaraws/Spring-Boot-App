package com.router.demo.config;

import java.util.Collections;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.router.demo.interceptor.RequestResponseLoggingInterceptor;

@Configuration
public class AppConfig {

	
	@Bean
	public RequestResponseLoggingInterceptor getLoggingInterceptor() {
		return new RequestResponseLoggingInterceptor();
	}
	
	@Bean
	@Qualifier("httpRestTemplate")
	public RestTemplate httpRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Collections.singletonList(getLoggingInterceptor()));
		return restTemplate;
	}
	
	@Bean
	@Qualifier("httpsRestTemplate")
	public RestTemplate httpsRestTemplate() {
		
		CloseableHttpClient httpClient
	      		= HttpClients.custom()
	      		  .setSSLHostnameVerifier(new NoopHostnameVerifier())
   	              .build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.setInterceptors(Collections.singletonList(getLoggingInterceptor()));
		return restTemplate;
	}
	
}
