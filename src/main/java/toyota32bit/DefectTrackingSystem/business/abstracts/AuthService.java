package toyota32bit.DefectTrackingSystem.business.abstracts;

import toyota32bit.DefectTrackingSystem.business.requests.AuthenticationRequest;
import toyota32bit.DefectTrackingSystem.business.requests.RegisterRequest;
import toyota32bit.DefectTrackingSystem.business.responses.AuthenticationResponse;

public interface AuthService {
	AuthenticationResponse register(RegisterRequest request);
	AuthenticationResponse authenticate(AuthenticationRequest request);
}
