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
		security = new Security("YES YES YES", "YYY");
		securityDaoImpl.save(security);
	}

	@Test
	public void testQueryById() {
		security = securityDaoImpl.queryById("YYY");
		assertEquals("YES YES YES", security.getSecurityName());
		assertEquals("YYY", security.getNameAbbreviation());
	}

	@Test
	public void testUpdate() {
		securityDaoImpl.update(new Security("YES YES YES YES", "YYY"));
		security = securityDaoImpl.queryById("YYY");
		assertEquals("YES YES YES YES", security.getSecurityName());
		assertEquals("YYY", security.getNameAbbreviation());
	}
	
	@Test
	public void testgetAll() {
		fevoriteSecurity = securityDaoImpl.queryAll();
		assertNotNull(fevoriteSecurity);
		assertEquals(23, fevoriteSecurity.size());
	}
	@Test
	public void testDelete() {
		securityDaoImpl.delete("YYY");
	}
	

}
