package com.citi.swifttrading.daoImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citi.swifttrading.daoImpl.SecurityDaoImpl;
import com.citi.swifttrading.domain.Security;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class SecurityDaoImplTest {

	@Autowired
	SecurityDaoImpl securityDaoImpl;

	Security security;

	List<Security> fevoriteSecurity;

	@Test
	public void testSave() {
		security = new Security("Name for ISIN10", "Name 10");
		securityDaoImpl.save(security);
	}

	@Test
	public void testQueryById() {
		security = securityDaoImpl.queryById("Name 10");
		assertEquals("Name for ISIN10", security.getSecurityName());
		assertEquals("Name 10", security.getNameAbbreviation());
	}

	@Test
	public void testUpdate() {
		securityDaoImpl.update(new Security("Name for Name 10", "Name 10"));
		security = securityDaoImpl.queryById("Name 10");
		assertEquals("Name for Name 10", security.getSecurityName());
		assertEquals("Name 10", security.getNameAbbreviation());
	}
	
	@Test
	public void testgetAll() {
		fevoriteSecurity = securityDaoImpl.queryAll();
		assertNotNull(fevoriteSecurity);
		assertEquals(7, fevoriteSecurity.size());
	}
	@Test
	public void testDeletel() {
		securityDaoImpl.delete("Name 3");
	}
	

}
