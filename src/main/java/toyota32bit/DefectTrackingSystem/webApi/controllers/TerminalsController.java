package toyota32bit.DefectTrackingSystem.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.TerminalService;

@RestController
@RequestMapping("/api/terminals")
@RequiredArgsConstructor
public class TerminalsController {
	
	private final TerminalService terminalService;
	
	@GetMapping("/get-terminals")
	public ResponseEntity<?> getTerminals(){
	
		return ResponseEntity.ok(terminalService.getTerminals());
	}
}
