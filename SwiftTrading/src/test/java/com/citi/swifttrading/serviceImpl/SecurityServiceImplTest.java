package com.citi.swifttrading.serviceImpl;

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
public class SecurityServiceImplTest {

	@Autowired
	SecurityServiceImpl securityServiceImpl;

	Security security;

	List<Security> fevoriteSecurity;

	@Test
	public void testSave() {
		security = new Security("Name for ISIN11", "Name 11");
		securityServiceImpl.save(security);
	}

	@Test
	public void testQueryById() {
		security = securityServiceImpl.queryById("Name 11");
		assertEquals("Name for ISIN11", security.getSecurityName());
		assertEquals("Name 11", security.getNameAbbreviation());
	}

	@Test
	public void testUpdate() {
		securityServiceImpl.update(new Security("Name for Name 11", "Name 11"));
		security = securityServiceImpl.queryById("Name 11");
		assertEquals("Name for Name 11", security.getSecurityName());
		assertEquals("Name 11", security.getNameAbbreviation());
	}

	@Test
	public void testDeletel() {
		securityServiceImpl.delete("Name 1");
	}

	@Test
	public void testgetAll() {
		fevoriteSecurity = securityServiceImpl.queryAll();
		assertNotNull(fevoriteSecurity);
		assertEquals(7, fevoriteSecurity.size());
	}

}
