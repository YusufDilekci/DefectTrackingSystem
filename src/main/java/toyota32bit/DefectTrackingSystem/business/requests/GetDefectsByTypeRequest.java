package toyota32bit.DefectTrackingSystem.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetDefectsByTypeRequest {
	
	private String type;

}
