package toyota32bit.DefectTrackingSystem.business.abstracts;

import java.util.List;

import toyota32bit.DefectTrackingSystem.business.requests.AssignRoleRequest;
import toyota32bit.DefectTrackingSystem.business.requests.CreateUserRequest;
import toyota32bit.DefectTrackingSystem.business.requests.DeleteUserRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetByMailRequest;
import toyota32bit.DefectTrackingSystem.business.requests.UpdateUserRequest;
import toyota32bit.DefectTrackingSystem.business.responses.GetAllUserResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetUserResponse;
import toyota32bit.DefectTrackingSystem.core.utilities.results.Result;

public interface UserService {
	List<GetAllUserResponse> getAll();
	GetUserResponse getByEmail(GetByMailRequest request);
	Result assignUserRole(AssignRoleRequest request);
	Result add(CreateUserRequest request);
	Result update(UpdateUserRequest request);
	Result delete(DeleteUserRequest request);
}
