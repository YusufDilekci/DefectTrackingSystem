package toyota32bit.DefectTrackingSystem.business.abstracts;


import java.util.List;

import toyota32bit.DefectTrackingSystem.business.requests.GetDefectsByTypeRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetLastRecordsRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetPageableDefectRequest;
import toyota32bit.DefectTrackingSystem.business.responses.GetDefectsByTypeResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetDefectsByVehicleResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetLastRecordsResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetLocationsByVehicleResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetPageableDefectResponse;

public interface DefectListingService {

	List<GetPageableDefectResponse> getByPageSortFilter(GetPageableDefectRequest request);
	List<GetLastRecordsResponse> getLastRecords(GetLastRecordsRequest request);
	byte[] getImage(int defectId) throws Exception;
	List<GetDefectsByVehicleResponse> getDefectByVehicle(int vehicleId);
	List<GetLocationsByVehicleResponse> getLocationsByVehicle(int vehicleId);
	List<GetDefectsByTypeResponse> getDefectsByType(GetDefectsByTypeRequest request);
}
