package toyota32bit.DefectTrackingSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
	
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String[] roles;
}
