//package toyota32bit.DefectTrackingSystem.dataAccess;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import toyota32bit.DefectTrackingSystem.core.entities.User;
//
//public interface UserRepository extends JpaRepository<User, Integer>{
//	
//	Optional<User> findByEmail(String email);
//	
//	@Modifying
//	@Query(value = "UPDATE users u SET u.deleted = :true where id = :userId")
//	void deleteById(@Param("userId") int userId);
//}
