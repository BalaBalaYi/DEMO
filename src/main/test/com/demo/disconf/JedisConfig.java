package com.demo.disconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * Redis�����ļ�
 * @author chenty
 *
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "redis.properties")
public class JedisConfig {

	private Logger logger = LoggerFactory.getLogger(JedisConfig.class);
	
	// �������ӵ�ַ
	private String host;

	// ��������port
	private int port;

	/**
	 * ��ַ, �ֲ�ʽ�ļ�����
	 *
	 * @return
	 */
	@DisconfFileItem(name = "redis.host", associateField = "host")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * �˿�, �ֲ�ʽ�ļ�����
	 *
	 * @return
	 */
	@DisconfFileItem(name = "redis.port", associateField = "port")
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
