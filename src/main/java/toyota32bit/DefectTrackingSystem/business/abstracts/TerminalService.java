package toyota32bit.DefectTrackingSystem.business.abstracts;

import java.util.List;

import toyota32bit.DefectTrackingSystem.core.utilities.results.DataResult;
import toyota32bit.DefectTrackingSystem.entities.Terminal;

public interface TerminalService {
	DataResult<List<Terminal>> getTerminals();
}
