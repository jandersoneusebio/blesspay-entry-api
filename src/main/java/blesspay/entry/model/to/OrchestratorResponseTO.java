package blesspay.entry.model.to;

import java.util.Objects;

import blesspay.entry.model.entity.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrchestratorResponseTO {

	private Integer statusCode;
	
	private String message;
	
	private Entry entry;
	
	private ErrorTO error;
	
	public boolean containsError() {
		return Objects.nonNull(error);
	}
}
