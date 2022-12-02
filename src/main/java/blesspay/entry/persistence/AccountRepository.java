package blesspay.entry.persistence;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blesspay.entry.model.entity.Account;
import blesspay.entry.model.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, BigInteger>{

	//@Query("select account_number + 1 from blesspay.accounts order by account_number desc")
	//public Optional<BigInteger> returnNextAccountNumber();
	
}
