package toyota32bit.DefectTrackingSystem.business.concretes;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.DefectListingService;
import toyota32bit.DefectTrackingSystem.business.requests.GetDefectsByTypeRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetLastRecordsRequest;
import toyota32bit.DefectTrackingSystem.business.requests.GetPageableDefectRequest;
import toyota32bit.DefectTrackingSystem.business.responses.GetDefectsByTypeResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetDefectsByVehicleResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetLastRecordsResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetLocationsByVehicleResponse;
import toyota32bit.DefectTrackingSystem.business.responses.GetPageableDefectResponse;
import toyota32bit.DefectTrackingSystem.core.utilities.mappers.ModelMapperService;
import toyota32bit.DefectTrackingSystem.dataAccess.DefectLocationRepository;
import toyota32bit.DefectTrackingSystem.dataAccess.VehicleDefectRepository;
import toyota32bit.DefectTrackingSystem.entities.DefectLocation;
import toyota32bit.DefectTrackingSystem.entities.VehicleDefect;

@Service
@RequiredArgsConstructor
public class DefectListingManager implements DefectListingService{
	
	private final VehicleDefectRepository defectRepository;
	private final ModelMapperService modelMapperService;
	private final DefectLocationRepository locationRepository;
	
	public List<GetPageableDefectResponse> getByPageSortFilter(GetPageableDefectRequest request){
		
		Pageable pageable = PageRequest.of(request.getPageNo() -1 , 
				request.getPageSize(), 
				Sort.by(request.getSortBy()).ascending());
		
		List<VehicleDefect> vehicleDefects = defectRepository.findAll(pageable).getContent();
		
		List<GetPageableDefectResponse> defectResponse = vehicleDefects.stream()
				.map(defect -> this.modelMapperService.forResponse()
						.map(defect, GetPageableDefectResponse.class))
				.collect(Collectors.toList());

				
		return defectResponse;
		
	}
	
	public List<GetLastRecordsResponse> getLastRecords(GetLastRecordsRequest request){
		Pageable pageable = PageRequest.of(0, request.getSize(), Sort.by(request.getSortBy()).descending());
		
		List<VehicleDefect> defects = defectRepository.findAll(pageable).getContent();
		
		List<GetLastRecordsResponse> response = defects.stream()
				.map(defect -> this.modelMapperService.forResponse()
						.map(defect, GetLastRecordsResponse.class))
				.collect(Collectors.toList());
		
		return response;
	}
	
	/**
	 * <p>
	 * 		This method works by getting defect id to invoke defected image path so assign
	 * 		InputStream type.
	 * </p>
	 * @param defectId 
	 * @return the requested byte array
	 */
	public byte[] getImage(int defectId) throws Exception{
		
        var imagePath = MessageFormat.format("/static/defectedImage/{0}.png", defectId);
        InputStream in = getClass()
        	      .getResourceAsStream(imagePath);
        	    
        return IOUtils.toByteArray(in);
        
	}

	@Override
	public List<GetDefectsByVehicleResponse> getDefectByVehicle(int vehicleId) {
		
		List<VehicleDefect> defects = defectRepository.getByVehicleId(vehicleId);
		
		List<GetDefectsByVehicleResponse> defectsResponse= defects.stream()
				.map(defect -> this.modelMapperService.forResponse()
						.map(defect, GetDefectsByVehicleResponse.class))
				.collect(Collectors.toList());

		
		return defectsResponse;
	}
	
	public List<GetLocationsByVehicleResponse> getLocationsByVehicle(int vehicleId){
		List<DefectLocation> locations = locationRepository.getByVehicleId(vehicleId);
		
		List<GetLocationsByVehicleResponse> locationResponse = locations.stream()
				.map(location -> this.modelMapperService.forResponse()
						.map(location,  GetLocationsByVehicleResponse.class))
				.collect(Collectors.toList());
		
		return locationResponse;
	}
	
	public List<GetDefectsByTypeResponse> getDefectsByType(GetDefectsByTypeRequest request){
			
		List<VehicleDefect> vehicleDefects = defectRepository.getByType(request.getType());
		
		List<GetDefectsByTypeResponse> response = vehicleDefects.stream()
				.map(defect -> this.modelMapperService.forResponse()
						.map(defect, GetDefectsByTypeResponse.class))
				.collect(Collectors.toList());
		
		return response;
	}
}
