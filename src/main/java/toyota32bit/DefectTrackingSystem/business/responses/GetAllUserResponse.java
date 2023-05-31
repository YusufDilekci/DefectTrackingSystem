package toyota32bit.DefectTrackingSystem.business.responses;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toyota32bit.DefectTrackingSystem.core.entities.Role;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserResponse {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private Set<Role> roles;
}