package com.router.demo.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.router.demo.config.AppProperties;

@Service
public class Utils {

	@Autowired
	private AppProperties properties;
	
	public int getShardIndex(String key) {
		int index=-1;
		List<String> listShardUrls = properties.getListUrlShard();
		int shardSpan = listShardUrls.size();
		byte[] byteArray = key.getBytes(); // getBytes() method is platform dependent, not advised to use in production
		index = byteArray[0] % shardSpan;
		return index; 
	}
}
