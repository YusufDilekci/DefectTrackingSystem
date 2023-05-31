package toyota32bit.DefectTrackingSystem.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetLocationsByVehicleResponse {
	
	private int id;
	private int defectId;
	private String location;

}
