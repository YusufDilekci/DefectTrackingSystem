package toyota32bit.DefectTrackingSystem.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import toyota32bit.DefectTrackingSystem.entities.VehicleDefect;

public interface VehicleDefectRepository extends JpaRepository<VehicleDefect, Integer>{
	@Modifying
	@Query(value = "UPDATE VehicleDefect vd SET vd.deleted = true where vd.id = :id")
	void deleteById(@Param("id") int id);
	
	@Modifying
	@Query(value = "UPDATE VehicleDefect v SET v.deleted = true where vehicle.id = :vehicleId")
	void deleteByVehicleId(@Param("vehicleId") int vehicleId);
	

	List<VehicleDefect> getByType(@Param("type") String type);
	
	
	@Query("SELECT v FROM VehicleDefect v where vehicle.id = :vehicleId")
	List<VehicleDefect> getByVehicleId(@Param("vehicleId") int vehicleId);
}
