package com.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CipherUtil {

	private static Logger logger = LoggerFactory.getLogger(CipherUtil.class);

//	//JbossPasswordDecode����
//	public static String JbossPasswordEncode(String str){
//		
//		String encodeStr = null;
//		try {
//			encodeStr = new String(SecureIdentityLoginModule.encode(str));
//		} catch (Exception e) {
//			logger.error("JbossPasswordDecode���ܷ����쳣", e);
//		}
//		return encodeStr; 
//	}
//	
//	//JbossPasswordDecode����
//	public static String JbossPasswordDecode(String str){
//		
//		String decodeStr = null;
//		try {
//			decodeStr = new String(SecureIdentityLoginModule.decode(str));
//		} catch (Exception e) {
//			logger.error("JbossPasswordDecode���ܷ����쳣", e);
//		}
//		return decodeStr; 
//	}
//	
//	public static void main(String[] args) {
//		System.out.println("Encoded:" + CipherUtil.JbossPasswordEncode("test"));
//		System.out.println("Decoded:" + CipherUtil.JbossPasswordDecode(""));
//	}
	
}
