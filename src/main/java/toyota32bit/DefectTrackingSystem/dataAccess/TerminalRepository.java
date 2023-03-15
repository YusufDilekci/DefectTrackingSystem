package toyota32bit.DefectTrackingSystem.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import toyota32bit.DefectTrackingSystem.entities.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, Integer>{

}
