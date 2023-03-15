package toyota32bit.DefectTrackingSystem.business.concretes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.MessageFormat;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toyota32bit.DefectTrackingSystem.business.abstracts.DefectEntryService;
import toyota32bit.DefectTrackingSystem.business.requests.CreateDefectRequest;
import toyota32bit.DefectTrackingSystem.business.requests.UpdateDefectRequest;
import toyota32bit.DefectTrackingSystem.core.utilities.mappers.ModelMapperService;
import toyota32bit.DefectTrackingSystem.core.utilities.results.ErrorResult;
import toyota32bit.DefectTrackingSystem.core.utilities.results.Result;
import toyota32bit.DefectTrackingSystem.core.utilities.results.SuccessResult;
import toyota32bit.DefectTrackingSystem.dataAccess.DefectLocationRepository;
import toyota32bit.DefectTrackingSystem.dataAccess.VehicleDefectRepository;
import toyota32bit.DefectTrackingSystem.entities.DefectLocation;
import toyota32bit.DefectTrackingSystem.entities.Vehicle;
import toyota32bit.DefectTrackingSystem.entities.VehicleDefect;

/**
 * 
 * @author Yusuf
 *
 */

@Service
@RequiredArgsConstructor
public class DefectEntryManager implements DefectEntryService{
	
	private final VehicleDefectRepository defectRepository;
	private final DefectLocationRepository locationRepository;
	private final ModelMapperService modelMapperService;

	@Override
	public Result add(CreateDefectRequest request) throws Exception {
		
		VehicleDefect defect = new VehicleDefect();
		
		Vehicle v = new Vehicle();
		v.setId(request.getVehicleId());
		
        defect.setVehicle(v);
		defect.setType(request.getType());
		defect.setImage(request.getFile().getOriginalFilename());

		defectRepository.save(defect);

        editLocation(request.getCoordinates(), defect);
		editDefectImage(request, defect);

		return new SuccessResult();
	}
	
	public Result delete(int id) {
		
		defectRepository.deleteById(id);
		
		return new SuccessResult("Defect silindi");
	}
	
	public Result update(UpdateDefectRequest request) {
		var defect = defectRepository.findById(request.getId());
		
		if(defect != null) {
			VehicleDefect vehicleDefect = this.modelMapperService.forRequest()
					.map(request, VehicleDefect.class);
			
			
			defectRepository.save(vehicleDefect);
			
			return new SuccessResult("Defect is updated");
		}
		
		return new ErrorResult("Bir hata meydana geldi");
		
	}

	/**
	 *
	 * <p>This method get String array and VehicleDefect class then split the array,
	 * 		convert appropriate format to set Location of DefectLocation after that save
	 * 		to database 
	 * </p>
	 * 
	 * @param locationArr 
	 * @param defect
	 * 
	 */
	
	private void editLocation(String[] locationArr, VehicleDefect defect){
	
		String[] coor;
		String x;
		String y;
		
		
		for(var coordinates : locationArr) {
			
			coor = coordinates.split(",");
			x = coor[0];
			y = coor[1];
			String term = x + "," + y;
			DefectLocation defectLocation = new DefectLocation();
			defectLocation.setLocation(term);
			defectLocation.setVehicleDefect(defect);
			locationRepository.save(defectLocation);
		}
	}
	
	/**
	 * <p>
	 * 		This method take CreateDefectRequest Dto and VehicleDefect's instance as parameter,
	 * 		getFile from request then edit image, mark the defect locations on image finally,
	 * 		save them into distinct folders.
	 * </p>
	 * 
	 * @param request
	 * @param defect
	 * @return the classes of Result Interface which can be Success or Error
	 * @throws Exception
	 */
	private Result editDefectImage(CreateDefectRequest request, VehicleDefect defect) throws Exception{
		
			String Path_Directory = "C:\\Users\\Jossoft\\Desktop\\Java\\ToyotaProject\\DefectTrackingSystem\\src\\main\\resources\\static\\";
			//String Path_Directory = new ClassPathResource("static/").getFile().getAbsolutePath();
			
			if(request.getFile() != null) {
				
				BufferedImage image = ImageIO.read(request.getFile().getInputStream()); 
				
		        var orginalPath = MessageFormat.format(Path_Directory + "image\\{0}.png", defect.getId());
		        File orginalFile = new File(orginalPath);
		        ImageIO.write(image, "png", orginalFile);
		        
		        BufferedImage pointer = ImageIO.read(new File(Path_Directory + "image\\pointer_image.jpg"));
		        
		         
		        Graphics2D g2d = image.createGraphics();
		        
		        for(var coor : request.getCoordinates()) {
		        	int x = Integer.parseInt(coor.split(",")[0]);
		        	int y = Integer.parseInt(coor.split(",")[1]);
		        	g2d.drawImage(pointer, x, y, null);
		        }
	       
		        g2d.dispose();
		        
		        var imagePath = MessageFormat.format(Path_Directory + "defectedImage\\{0}.png", defect.getId());
		        File outputfile = new File(imagePath);
		        ImageIO.write(image, "png", outputfile);
		        
		        return new SuccessResult("Image başarılı şekilde düzenlendi");
			}
			
	     
	        return new ErrorResult("Image uygun bir şekilde yüklenemedi");
	       
	}
	

}
