package toyota32bit.DefectTrackingSystem.webApi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.DefectListingService;
import toyota32bit.DefectTrackingSystem.business.requests.GetDefectsByTypeRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetLastRecordsRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetPageableDefectRequest;

@RestController
@RequestMapping("/api/defect-listing")
@RequiredArgsConstructor
public class DefectListingsController {
	
	private final DefectListingService defectListingService;
	private final Logger logger = LoggerFactory.getLogger(DefectListingsController.class);
	
	@GetMapping(value = "/defect-image/{defectId}",
			produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> getImage(@PathVariable("defectId") int defectId) throws Exception{
        	    
        return ResponseEntity.ok(defectListingService.getImage(defectId));
        
	}
	
	@GetMapping("/getall-pageable-defect")
 	public ResponseEntity<?> getByPageSortFilter(@RequestBody GetPageableDefectRequest request){
		
		return ResponseEntity.ok(defectListingService.getByPageSortFilter(request));
	}
	
	@GetMapping("/getlast-defect-records")
	public ResponseEntity<?> getLastRecords(@RequestBody GetLastRecordsRequest request){
		
		return ResponseEntity.ok(defectListingService.getLastRecords(request));
	}
	
	@GetMapping("/getDefectsByVehicle/{vehicleId}")
	public ResponseEntity<?> getDefectByVehicle(@PathVariable("vehicleId") int vehicleId){
		
		return ResponseEntity.ok(defectListingService.getDefectByVehicle(vehicleId));
	}
	
	@GetMapping("/getLocationsByVehicle/{vehicleId}")
	public ResponseEntity<?> getLocationsByVehicle(@PathVariable("vehicleId") int vehicleId){
		
		return ResponseEntity.ok(defectListingService.getLocationsByVehicle(vehicleId));
	}
	
	@GetMapping("/getDefectsByType")
	public ResponseEntity<?> getDefectsByType(@RequestBody GetDefectsByTypeRequest request){
		
		return ResponseEntity.ok(defectListingService.getDefectsByType(request));
	} 
	
}
