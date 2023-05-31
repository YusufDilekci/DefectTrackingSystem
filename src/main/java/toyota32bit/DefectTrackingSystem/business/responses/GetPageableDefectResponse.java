package toyota32bit.DefectTrackingSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPageableDefectResponse {
	
	private int id;
	private String type;
	private String image;
	private String vehicleName; 
}
