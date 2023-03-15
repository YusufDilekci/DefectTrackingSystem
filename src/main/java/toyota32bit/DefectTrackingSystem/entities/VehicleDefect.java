package toyota32bit.DefectTrackingSystem.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vehicle_defects")
@Entity
@Where(clause = "deleted=false")
public class VehicleDefect {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "deleted")
	private boolean deleted;
	
	@Column(name= "image")
	private String image;
	
	@ManyToOne
    @JoinColumn(name="vehicle_id", nullable=false)
    private Vehicle vehicle;
	
	@OneToMany(mappedBy="vehicleDefect")
    private Set<DefectLocation> defectLocation = new HashSet<>();
}
