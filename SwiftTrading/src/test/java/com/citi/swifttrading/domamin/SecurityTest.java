package com.citi.swifttrading.domamin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.citi.swifttrading.domain.Security;


public class SecurityTest {
	
	Security security;
	
	@Before
	public void setUp() {
		security = new Security("ISIN123456", "This is Security Name", "Short Name", true);
	}

	@Test
	public void testUserGet() {
		assertEquals("ISIN123456", security.getISIN());
		assertEquals("This is Security Name", security.getSecurityName());
		assertEquals("Short Name", security.getNameAbbreviation());
		assertTrue(security.isFavorite());
	}
	
	@Test
	public void testUserSet() {
		security.setISIN("ISIN654321");
		security.setSecurityName("Change Name");
		security.setNameAbbreviation("Change Short Name");
		security.setFavorite(false);
		
		assertEquals("ISIN654321", security.getISIN());
		assertEquals("Change Name", security.getSecurityName());
		assertEquals("Change Short Name", security.getNameAbbreviation());
		assertFalse(security.isFavorite());
	}
}
