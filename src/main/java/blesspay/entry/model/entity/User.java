package blesspay.entry.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -510279246951658667L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name = "document")
	private String document;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@Column(name = "situation")
	private String situation;
	
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;

}
