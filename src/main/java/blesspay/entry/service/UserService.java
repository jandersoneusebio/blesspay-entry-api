package blesspay.entry.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blesspay.entry.model.entity.Account;
import blesspay.entry.model.entity.User;
import blesspay.entry.model.to.ErrorTO;
import blesspay.entry.model.to.UserLoginResponse;
import blesspay.entry.persistence.AccountRepository;
import blesspay.entry.persistence.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public void signUp() {
		
		//BigInteger an = accountRepository.returnNextAccountNumber().get();
		
		Account account = new Account();
		account.setAccountBranch("11");
		account.setAccountNumber("1234");
		account.setBalance(BigDecimal.valueOf(200.0));
		account.setDocument("70316699446");
		
		Account persistedAccount = accountRepository.saveAndFlush(account);
		
		User user = new User();
		user.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
		user.setPassword("teste");
		user.setSituation("0");
		user.setAccount(persistedAccount);
		
		userRepository.save(user);
		
	}
	
	public UserLoginResponse login(String document, String password) {
		User user = userRepository.findFirstByDocumentAndPassword(document, password);
	
		if(user != null) {
			return new UserLoginResponse(user, UUID.randomUUID().toString());
		} else {
			return null;
		}
		
	}
	
}
