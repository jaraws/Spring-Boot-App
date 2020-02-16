package com.router.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.router.demo.config.AppProperties;
import com.router.demo.service.RouterService;

@RestController
@RequestMapping("/cache-router")
public class RouterController {
	
	private static final Logger logger = LoggerFactory.getLogger(RouterController.class);

	@Autowired
	private AppProperties properties;
	
	@Autowired
	private RouterService routerService;
	
	@GetMapping("/status")
	public String getStatusMessage() {
		return properties.getStatusMessage();
	}
	
	@GetMapping(path="/get", produces = "application/json")
	public String getCache() {
		logger.info("Request received for getCache");
		String cacheString = routerService.getCache();
		return cacheString;
	}
	
	@GetMapping(path="/put", produces = "application/json")
	public String putCache(@RequestParam(value = "key") String key,@RequestParam(value = "value") String value) {
		logger.info("Request received for putCache [{},{}]",key,value);
		String response = routerService.putCache(key,value);
		return response;
	}
	
	@GetMapping(path="/remove", produces = "application/json")
	public String removeCache(@RequestParam(value = "key") String key) {
		logger.info("Request received for removeCache [{}]",key);
		String response = routerService.removeCache(key);
		return response;
	}

}
