package com.router.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.router.demo.config.AppProperties;
import com.router.demo.utils.Constants;
import com.router.demo.utils.Utils;

@Service
public class RouterService {
	
	private static final Logger logger = LoggerFactory.getLogger(RouterService.class);
	
	@Autowired
	private AppProperties properties;
	
	@Autowired
	private Utils routerUtils;
	
	@Autowired
	@Qualifier("httpsRestTemplate")
	private RestTemplate restTemplate;
	
	public String getCache() {
		List<String> shardUrls = properties.getListUrlShard();
		String shardGetPath = properties.getShardGetPath();
		String response = Constants.EMPTY_STRING;
		
		for(String url: shardUrls) {
			response = response +"["+ restTemplate.getForObject(url+shardGetPath, String.class) +"]";
		}
		logger.info("Get Cache Response: {}",response);
		return response;
	}
	
	public String putCache(String key,String value) {
		int shardIndex = routerUtils.getShardIndex(key);
		String targetIPPort = properties.getListUrlShard().get(shardIndex);
		String shardPutPath = properties.getShardPutPath();
		String url = targetIPPort+shardPutPath+"?key="+key+"&value="+value;
		String response = restTemplate.getForObject(url, String.class);
		logger.info("Put Cache Response: {}",response);
		return response;
	}
	
	public String removeCache(String key) {
		int shardIndex = routerUtils.getShardIndex(key);
		String targetIPPort = properties.getListUrlShard().get(shardIndex);
		String shardRemovePath = properties.getShardRemovePath();
		String url = targetIPPort+shardRemovePath+"?key="+key;
		String response = restTemplate.getForObject(url, String.class);
		logger.info("Put Cache Response: {}",response);
		return response;
	}

}
