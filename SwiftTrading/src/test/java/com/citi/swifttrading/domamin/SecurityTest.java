package com.citi.swifttrading.domamin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Security222;


public class SecurityTest {
	
	Security222 security;
	
	@Before
	public void setUp() {
		security = new Security222("This is Security Name", "Short Name");
	}

	@Test
	public void testSecurityGet() {
		assertEquals("This is Security Name", security.getSecurityName());
		assertEquals("Short Name", security.getNameAbbreviation());
	}
	
	@Test
	public void testSecuritySet() {
		security.setSecurityName("Change Name");
		security.setNameAbbreviation("Change Short Name");
		
		assertEquals("Change Name", security.getSecurityName());
		assertEquals("Change Short Name", security.getNameAbbreviation());
	}
}
