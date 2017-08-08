package com.citi.swifttrading.domamin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Security;

public class SecurityTest {

	Security security;

	@Before
	public void setUp() {
		security = new Security("This is Security Name", "Short Name");
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
