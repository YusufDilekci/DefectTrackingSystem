package toyota32bit.DefectTrackingSystem.business.concretes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.UserService;
import toyota32bit.DefectTrackingSystem.business.requests.AssignRoleRequest;
import toyota32bit.DefectTrackingSystem.business.requests.CreateUserRequest;
import toyota32bit.DefectTrackingSystem.business.requests.DeleteUserRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetByMailRequest;
import toyota32bit.DefectTrackingSystem.business.requests.UpdateUserRequest;
import toyota32bit.DefectTrackingSystem.business.responses.GetAllUserResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetUserResponse;
import toyota32bit.DefectTrackingSystem.core.entities.Role;
import toyota32bit.DefectTrackingSystem.core.entities.User;
import toyota32bit.DefectTrackingSystem.core.utilities.mappers.ModelMapperService;
import toyota32bit.DefectTrackingSystem.core.utilities.results.ErrorResult;
import toyota32bit.DefectTrackingSystem.core.utilities.results.Result;
import toyota32bit.DefectTrackingSystem.core.utilities.results.SuccessResult;
import toyota32bit.DefectTrackingSystem.dataAccess.RoleRepository;
import toyota32bit.DefectTrackingSystem.dataAccess.UserRepository;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService{
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final ModelMapperService modelMapperService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public List<GetAllUserResponse> getAll() {
		var users = userRepository.findAll();
		
		List<GetAllUserResponse> response= users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllUserResponse.class))
				.collect(Collectors.toList());
		
		return response;
	}
	@Override
	public GetUserResponse getByEmail(GetByMailRequest request) {
		
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		
		GetUserResponse response = this.modelMapperService.forResponse().map(user, GetUserResponse.class);
		
		return response;
	}
	@Override
	public Result assignUserRole(AssignRoleRequest request) {
		var user = userRepository.findByEmail(request.getEmail()).get();
		
		var roles = toSetRole(request.getRoles());
		
		for(var r: roles) {
			var role = roleRepository.findByName(r.toString());
			if(role == null) {
				
				return new ErrorResult("Role was not added, please enter valid role.");
			}
			user.addRole(role);
		}
		
		
		userRepository.save(user);
		return new SuccessResult("Role added");
		
		
		
	}

	@Override
	public Result add(CreateUserRequest request) {
		User user  = new User();
		
		user.setRoles(toSetRole(request.getRoles()));
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
		
		
		return new SuccessResult("Kullanıcı eklendi"); 
	}
	
	private Set<Role> toSetRole(String[] roles){
		Set<Role> arr= new HashSet<>();
		for(int i=0; i < roles.length; i++) {
			var role = roleRepository.findByName(roles[i]);
			arr.add(role);
		}
		
		return arr;
		
	}

	@Override
	public Result update(UpdateUserRequest request) {

		User user = userRepository.findById(request.getId()).orElseThrow();
		
		user.setEmail(request.getEmail());
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(toSetRole(request.getRoles()));
		
		userRepository.save(user);
		
		return new SuccessResult("Kullanıcı başarılı bir şekilde güncellendi");
	}

	@Override
	public Result delete(DeleteUserRequest request) {
		
		userRepository.deleteById(request.getId());
		return new SuccessResult("Kullanıcı başarıyla soft silindi");
	}

}
