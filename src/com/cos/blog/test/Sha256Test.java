package com.cos.blog.test;

import java.security.MessageDigest;

import org.junit.Test;

public class Sha256Test {
	private final static String salt = "코스";

	@Test
	public void encSha256() {
		String plain = "1234";
		String result = "";

		byte[] bytePlain = plain.getBytes(); // byte화 됨 byte 단위로 쪼갠다.
		byte[] byteSalt = salt.getBytes();

		for (byte b : bytePlain) {
		//	System.out.print(b + " ");
		}
		System.out.println();

		for (byte c : byteSalt) {
		//	System.out.print(c + " ");
		}

		byte[] bytePlainAndSalt = new byte[bytePlain.length + byteSalt.length];

		
		System.arraycopy
		(
				bytePlain, 
				0, 
				bytePlainAndSalt, 
				0, 
				bytePlain.length
		);
		
		System.arraycopy
		(
				byteSalt, 
				0, 
				bytePlainAndSalt, 
				bytePlain.length, 
				byteSalt.length
		);
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytePlainAndSalt);
			
			byte[] byteData = md.digest(); //꺼내쓸때는 digest
			
			StringBuffer sb = new StringBuffer(); //동시접근 불가 , 크리티컬 섹션, 임계구역
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toHexString((byteData[i] & 0xFF)+256).substring(1)); // 16진수로 변환하기
			}
			result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			
		}
		
	}
}