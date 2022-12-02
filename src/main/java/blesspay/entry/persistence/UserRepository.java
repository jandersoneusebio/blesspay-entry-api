package blesspay.entry.persistence;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blesspay.entry.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>{

	public User findFirstByDocumentAndPassword(String document, String password);
}
