package toyota32bit.DefectTrackingSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import toyota32bit.DefectTrackingSystem.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
	
	@Modifying
	@Query(value = "UPDATE Vehicle v SET v.deleted = true Where v.id = :id")
	void deleteById(@Param("id") int id);
}
