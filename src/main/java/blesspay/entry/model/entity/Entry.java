package blesspay.entry.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import blesspay.entry.model.to.CreateEntryTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry implements Serializable {

	private static final long serialVersionUID = -5134735508746217300L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name = "entry")
	private String entry;
	
	@Column(name = "entry_type")
	private String entryType;
	
	@Column(name = "account_branch")
	private BigInteger accountBranch;
	
	@Column(name = "account_number")
	private BigInteger accountNumber;
	
	@Column(name = "account_document")
	private String accountDocument;
	
	@Column(name = "create_date")
	private Timestamp creationDate;
	
	@Column(name = "update_date")
	private Timestamp updateDate;
	
	@Column(name = "situation")
	private Integer situation;
	
	public static Entry fromCreateEntryTO(CreateEntryTO createEntryTO) {
		Entry newEntry = new Entry();
		newEntry.setEntry(createEntryTO.getEntry());
		newEntry.setEntryType(createEntryTO.getEntryType());
		newEntry.setAccountBranch(createEntryTO.getAccountBranch());
		newEntry.setAccountNumber(createEntryTO.getAccountNumber());
		newEntry.setAccountDocument(createEntryTO.getAccountDocument());
		newEntry.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
		newEntry.setSituation(1);
		return newEntry;
	}

}
