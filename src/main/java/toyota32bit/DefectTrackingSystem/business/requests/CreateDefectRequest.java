package toyota32bit.DefectTrackingSystem.business.requests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDefectRequest {
	
	private int vehicleId;
	private String type;
	private String[] coordinates;
	private MultipartFile file;
}
