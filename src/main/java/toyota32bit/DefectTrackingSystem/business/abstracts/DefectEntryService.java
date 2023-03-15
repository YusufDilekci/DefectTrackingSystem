package toyota32bit.DefectTrackingSystem.business.abstracts;

import toyota32bit.DefectTrackingSystem.business.requests.CreateDefectRequest;
import toyota32bit.DefectTrackingSystem.business.requests.UpdateDefectRequest;
import toyota32bit.DefectTrackingSystem.core.utilities.results.Result;

public interface DefectEntryService {
	
	Result add(CreateDefectRequest request) throws Exception;
	Result delete(int id);
	Result update(UpdateDefectRequest request);
}
