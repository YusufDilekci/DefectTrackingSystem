package toyota32bit.DefectTrackingSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import toyota32bit.DefectTrackingSystem.core.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(
			  value = "SELECT * FROM roles where name = :roleName", 
			  nativeQuery = true)
	public Role findByName(@Param("roleName") String roleName);
}
