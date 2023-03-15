package toyota32bit.DefectTrackingSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toyota32bit.DefectTrackingSystem.entities.Vehicle;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDefectRequest {
	
	private int id;
	private String type;
	private Vehicle vehicle;
	
}
