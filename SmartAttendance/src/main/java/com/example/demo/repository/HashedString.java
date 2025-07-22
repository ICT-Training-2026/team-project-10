package com.example.demo.repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashedString {
	public String hashe_sha256(String target) {
		String result;
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] cipherBytes = md.digest(target.getBytes());

			for (int i=0; i<cipherBytes.length; i++) {
				sb.append(String.format("%02x", cipherBytes[i]&0xff));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			result = "NoSuchAlgorithmException";
		}
	
		return result;
	}
}
