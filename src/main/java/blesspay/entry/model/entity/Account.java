package blesspay.entry.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 5564649345585159526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name = "document")
	private String document;
	
	@Column(name = "account_branch")
	private String accountBranch;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "balance")
	private BigDecimal balance;

}
