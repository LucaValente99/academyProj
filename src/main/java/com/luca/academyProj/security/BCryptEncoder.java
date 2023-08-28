package com.luca.academyProj.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder {
	public static String encode(String psw) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(psw);
	}
}
