package blesspay.entry.model.to;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEntryTO {
	
	private String entry;

	private String entryType;
	
	private BigInteger accountBranch;
	
	private BigInteger accountNumber;
	
	private String accountDocument;

}
