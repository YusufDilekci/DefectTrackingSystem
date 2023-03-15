package toyota32bit.DefectTrackingSystem.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.DefectEntryService;
import toyota32bit.DefectTrackingSystem.business.requests.CreateDefectRequest;
import toyota32bit.DefectTrackingSystem.business.requests.UpdateDefectRequest;

@RestController
@RequestMapping("/api/defect-entries")
@RequiredArgsConstructor
public class DefectEntriesController {
	
	private final DefectEntryService defectEntryService;
	
	@RequestMapping(value="/add-defect", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<?> add(@ModelAttribute CreateDefectRequest request) throws Exception{
		
		return ResponseEntity.ok(defectEntryService.add(request)); 
	}
	
	@GetMapping("/delete-defect/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id){
		
		return ResponseEntity.ok(defectEntryService.delete(id));
	}
	
	@GetMapping("/update-defect")
	public ResponseEntity<?> delete(@RequestBody UpdateDefectRequest request){
		
		return ResponseEntity.ok(defectEntryService.update(request));
	}
	
}
