package toyota32bit.DefectTrackingSystem.business.concretes;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.AuthService;
import toyota32bit.DefectTrackingSystem.business.requests.AuthenticationRequest;
import toyota32bit.DefectTrackingSystem.business.requests.RegisterRequest;
import toyota32bit.DefectTrackingSystem.business.responses.AuthenticationResponse;
import toyota32bit.DefectTrackingSystem.core.entities.User;
import toyota32bit.DefectTrackingSystem.core.utilities.security.jwt.JwtService;
import toyota32bit.DefectTrackingSystem.dataAccess.RoleRepository;
import toyota32bit.DefectTrackingSystem.dataAccess.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService{
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public AuthenticationResponse register(RegisterRequest request) {

		var role = roleRepository.findByName("USER");
		
		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(Set.of(role))
				.deleted(false)
				.build();
		

		userRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
						)
				);
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
	}
}
