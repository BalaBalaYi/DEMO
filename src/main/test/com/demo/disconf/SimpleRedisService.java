package com.demo.disconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

/**
 * Redis���ò���
 * @author chenty
 *
 */
@Service
@Scope("singleton")
public class SimpleRedisService implements InitializingBean, DisposableBean {

	private Logger logger = LoggerFactory.getLogger(SimpleRedisService.class);
	
	// jedis ʵ��
	private Jedis jedis = null;

	/**
	 * �ֲ�ʽ����
	 */
	@Autowired
	private JedisConfig jedisConfig;

	/**
	 * �ر�
	 */
	public void destroy() throws Exception {
	
		if (jedis != null) {
			jedis.disconnect();
		}
	}

	/**
	 * ��������
	 */
	public void afterPropertiesSet() throws Exception {
	
		jedis = JedisUtil.createJedis(jedisConfig.getHost(),
				jedisConfig.getPort());
	}

	/**
	 * ��ȡһ��ֵ
	 *
	 * @param key
	 * @return
	 */
	public String getKey(String key) {
		if (jedis != null) {
			return jedis.get(key);
		}
	
		return null;
	}
	
	/**
	 * ����Jedis
	 */
	public void changeJedis() {

		logger.info("start to change jedis hosts to: " + jedisConfig.getHost()
			+ " : " + jedisConfig.getPort());
		
		jedis = JedisUtil.createJedis(jedisConfig.getHost(),
				jedisConfig.getPort());
		
		logger.info("change ok.");
	}
}
