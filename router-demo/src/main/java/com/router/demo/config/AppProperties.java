package com.router.demo.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProperties {

	private String statusMessage;
	private List<String> listUrlShard;
	private String shardGetPath;
	private String shardPutPath;
	private String shardRemovePath;

	public String getShardGetPath() {
		return shardGetPath;
	}

	public void setShardGetPath(String shardGetPath) {
		this.shardGetPath = shardGetPath;
	}

	public String getShardPutPath() {
		return shardPutPath;
	}

	public void setShardPutPath(String shardPutPath) {
		this.shardPutPath = shardPutPath;
	}

	public String getShardRemovePath() {
		return shardRemovePath;
	}

	public void setShardRemovePath(String shardRemovePath) {
		this.shardRemovePath = shardRemovePath;
	}

	public List<String> getListUrlShard() {
		return listUrlShard;
	}

	public void setListUrlShard(List<String> listUrlShard) {
		this.listUrlShard = listUrlShard;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
