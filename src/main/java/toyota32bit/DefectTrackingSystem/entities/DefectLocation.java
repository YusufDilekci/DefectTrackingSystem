package toyota32bit.DefectTrackingSystem.entities;

import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="defect_locations")
@Entity
@Where(clause = "deleted=false")
public class DefectLocation {
	
	public DefectLocation(VehicleDefect defect) {
		this.vehicleDefect = defect;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="defect_id", nullable=false)
    private VehicleDefect vehicleDefect;
	
	private String location;
	
	private boolean deleted = Boolean.FALSE;
}
