package toyota32bit.DefectTrackingSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toyota32bit.DefectTrackingSystem.entities.Vehicle;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPageableDefectResponse {
	
	private int id;
	private String type;
	private String image;
	private Vehicle vehicle;
}
