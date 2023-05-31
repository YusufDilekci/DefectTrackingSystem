package toyota32bit.DefectTrackingSystem.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import toyota32bit.DefectTrackingSystem.entities.DefectLocation;


public interface DefectLocationRepository extends JpaRepository<DefectLocation, Integer>{
	
	@Query("SELECT l FROM DefectLocation l JOIN l.vehicleDefect d JOIN d.vehicle v WHERE v.id = :vehicleId")
	List<DefectLocation> getByVehicleId(@Param("vehicleId") int vehicleId);
}
