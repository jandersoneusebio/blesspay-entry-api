package blesspay.entry.model.to;

import blesspay.entry.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
	
	private String document;
	
	private String accountBranch;
	
	private String accountNumber;
	
	private String token;
	
	public UserLoginResponse(User user, String token) {
		this.document = user.getAccount().getDocument();
		this.accountBranch = user.getAccount().getAccountBranch();
		this.accountNumber = user.getAccount().getAccountNumber();
		this.token = token;
	}

}
