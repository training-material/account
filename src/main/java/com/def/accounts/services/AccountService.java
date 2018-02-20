package com.def.accounts.services;


import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.def.accounts.domain.Account;
import com.def.accounts.exceptions.NoRecordsFoundException;
import com.def.accounts.repositories.AccountRepository;


@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	/**
	 * The accountRepository repository.
	 */
	@Autowired
	AccountRepository accountRepository;

	/**
	 * Retrieve an account with given id.
	 * The id here is the unique id value of the account managed by the repository (auto-increment).
	 * 
	 * @param id The id of the account.
	 * @return The account object if found or throws a NoRecordsFoundException.
	 */
	public Account getAccount(Integer id) {

		logger.debug("AccountService.getAccount: id=" + id);

		Account account = accountRepository.findOne(id);
		if (account == null) {
			logger.warn("AccountService.getAccount: could not find account with id: " + id);
			throw new NoRecordsFoundException();
		}

		logger.info(String.format("AccountService.getAccount - retrieved account with id: %s. Payload is: %s", id, account));

		return account;
	}

	/**
	 * Retrieve an account with given id.
	 * The id here is the unique user id value of the account, ie the username.
	 * 
	 * @param id The user id of the account.
	 * @return The account object if found or throws a NoRecordsFoundException.
	 */
	public Account getAccount(String id) {

		logger.debug("AccountService.getAccount: id=" + id);

		Account account = accountRepository.findByUserid(id);
		if (account == null) {
			logger.warn("AccountService.getAccount: could not find account with id: " + id);
			throw new NoRecordsFoundException();
		}

		logger.info(String.format("AccountService.getAccount - retrieved account with id: %s. Payload is: %s", id, account));

		return account;
	}

	

	/**
	 * Saves the given account in the repository.
	 * 
	 * @param accountRequest The account to save.
	 * @return the id of the account.
	 */
	public Integer saveAccount(Account accountRequest) {

		logger.debug("AccountService.saveAccount:" + accountRequest.toString());
		// need to set some stuff that cannot be null!
		if (accountRequest.getLogincount() == null) accountRequest.setLogincount(0);
		if (accountRequest.getLogoutcount() == null) accountRequest.setLogoutcount(0);
		if(accountRequest.getCreationdate() == null) accountRequest.setCreationdate(new Date());


		Account account = accountRepository.save(accountRequest);
		logger.info("AccountService.saveAccount: account saved: " + account);
		return account.getId();
	}

	
	

	/**
	 * Only positive amounts can be added to the balance.
	 * @param amount
	 * @param userId
	 * @return
	 */
	public double increaseBalance(double amount, String userId) {

		Account accountResponse = accountRepository.findByUserid(userId);

		BigDecimal currentBalance = accountResponse.getBalance();

		BigDecimal newBalance = currentBalance.add(new BigDecimal(amount));

		if(amount > 0.0){
			logger.debug("AccountController.increaseBalance: new balance='" + newBalance + "'.");
			accountResponse.setBalance(newBalance);
			saveAccount(accountResponse);
		}

		return accountResponse.getBalance().doubleValue();

	}

	/**
	 *
	 * @param amount
	 * @param accountId
	 * @return
	 */
	public double decreaseBalance(double amount, String accountId) {

		Account accountResponse = accountRepository.findByUserid(accountId);

		BigDecimal currentBalance = accountResponse.getBalance();

		if(currentBalance.doubleValue() >= amount){
            BigDecimal newBalance = currentBalance.subtract(new BigDecimal(amount));
			logger.debug("AccountController.decreaseBalance: new balance='" + newBalance + "'.");
			accountResponse.setBalance(newBalance);
			saveAccount(accountResponse);
		}

		return accountResponse.getBalance().doubleValue();

	}
}
