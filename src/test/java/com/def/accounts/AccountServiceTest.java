package com.def.accounts;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.def.accounts.domain.Account;
import com.def.accounts.exceptions.NoRecordsFoundException;
import com.def.accounts.repositories.AccountRepository;
import com.def.accounts.services.AccountService;

/**
 * Tests for the AccountService.
 * 
 * @author David Ferreira Pinto
 *
 */
public class AccountServiceTest {
	MockMvc mockMvc;

	@InjectMocks
	AccountService service;

	@Mock
	AccountRepository repo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

	/**
	 * test retrieval of account by integer.
	 */
	@Test
	public void doFindAccount() {
		when(repo.findOne(ServiceTestConfiguration.PROFILE_ID)).thenReturn(ServiceTestConfiguration.account());
		assertEquals(service.getAccount(ServiceTestConfiguration.PROFILE_ID).toString(),ServiceTestConfiguration.account().toString());
	}
	/**
	 * test retrieval of account by string - userid.
	 */
	@Test
	public void doFindAccountUserId() {
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(ServiceTestConfiguration.account());
		assertEquals(service.getAccount(ServiceTestConfiguration.USER_ID).toString(),ServiceTestConfiguration.account().toString());
	}
	/**
	 * test retrieval of account by string - userid, with no account found.
	 */
	@Test(expected=NoRecordsFoundException.class)
	public void doFindAccountUserIdNotFound() {
		when(repo.findByUserid(ServiceTestConfiguration.BAD_USER_ID)).thenReturn(null);
		service.getAccount(ServiceTestConfiguration.BAD_USER_ID);
	}
	/**
	 * test retrieval of account not found.
	 */
	@Test(expected=NoRecordsFoundException.class)
	public void doFindNullAccount() {
		when(repo.findOne(999)).thenReturn(null);
		service.getAccount(999);
	}
	
	
	/**
	 * test saving of account.
	 */
	@Test
	public void saveAccount() {
		Account acc = ServiceTestConfiguration.account();
		when(repo.save(acc)).thenReturn(acc);
		assertEquals(service.saveAccount(acc),acc.getId());
	}
	
	/**
	 * test saving of account with nulls.
	 */
	@Test
	public void saveAccountWithNullCounts() {
		Account acc = ServiceTestConfiguration.account();
		Account accNull = ServiceTestConfiguration.account();
		accNull.setLogincount(null);
		accNull.setLogoutcount(null);
		acc.setLogincount(0);
		acc.setLogoutcount(0);
		
		when(repo.save(acc)).thenReturn(acc);
		assertEquals(service.saveAccount(accNull),acc.getId());
	}
	
	
	
	
	/**
	 * Test Account domain object hashcode.
	 */
	@Test
	public void testAccountObject() {
		Account acc1 = ServiceTestConfiguration.account();
		Account acc2 = ServiceTestConfiguration.account();
		
		assertEquals(acc1.hashCode(),acc2.hashCode());
	}
}
