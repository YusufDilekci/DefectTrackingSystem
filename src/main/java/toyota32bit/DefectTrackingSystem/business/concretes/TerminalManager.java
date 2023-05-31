package toyota32bit.DefectTrackingSystem.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.TerminalService;
import toyota32bit.DefectTrackingSystem.core.utilities.results.DataResult;
import toyota32bit.DefectTrackingSystem.core.utilities.results.SuccessDataResult;
import toyota32bit.DefectTrackingSystem.dataAccess.TerminalRepository;
import toyota32bit.DefectTrackingSystem.entities.Terminal;

@Service
@RequiredArgsConstructor
public class TerminalManager implements TerminalService{
	
	private final TerminalRepository terminalRepository;
	
	@Override
	public DataResult<List<Terminal>> getTerminals() {
		
		return new SuccessDataResult<List<Terminal>>("Active terminals shown",terminalRepository.findAll());
	}
	
}
