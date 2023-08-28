package com.luca.academyProj;

import org.junit.jupiter.api.Test;

import com.luca.academyProj.security.BCryptEncoder;

class BCryptPasswordEncoderTest {

	@Test
	void test() {
        String rawPassword = "Luca@vale2";
        String encodedPassword = BCryptEncoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
	}
}
