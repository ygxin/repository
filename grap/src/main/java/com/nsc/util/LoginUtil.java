package com.nsc.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class LoginUtil {
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };
	
	//密码加密
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	//获取uuid
	public static String getUuid() {
	    UUID uuid=UUID.randomUUID();
	    String str = uuid.toString();
	    return str.replace("-", ""); 
	}
	/*
	 public static void main(String[] args) {
		 //System.out.println(getUuid());
		 System.out.println(MD5Encode("adminc6b64b1f0fc54b68bf3b3b4df698d4e4"));
		}*/
}
