package backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import backend.form.FileUpload;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String displayForm() {
        return "uploadfile";
    }
	
	@RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    public String fileSave(
            @ModelAttribute("uploadForm") FileUpload uploadForm,
            Model map,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
//		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
        String saveDirectory = request.getSession().getServletContext().getRealPath("/upload");
        File dir = new File(saveDirectory + File.separator + "tmpFiles");
		if (!dir.exists())	dir.mkdirs();
		String result = "";
 
        List<MultipartFile> crunchifyFiles = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
 
        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
        	File tempFile;
            for (MultipartFile multipartFile : crunchifyFiles) {
 
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                    // Handle file content - multipartFile.getInputStream()
                	tempFile = new File(dir.getAbsolutePath() +File.separator + fileName);
                    multipartFile.transferTo(tempFile);
//                    File f = ImageCompressor.INSTANCE.compress(tempFile, dir.getAbsolutePath());
                    byte[] by = ImageCompressor.INSTANCE.compress(tempFile);
                    int[][][] res = ImageCompressor.INSTANCE.toIntArray(by);
                    String urlParameters = ImageCompressor.INSTANCE.wrapupToString(res);
                    result = ImageCompressor.INSTANCE.executePost("http://54.89.204.178:5000/test_image", urlParameters);
                    System.out.println("RESPONESE:"+result);
                    fileNames.add(fileName);
                }
            }
            System.out.println(result.substring(19,20));
        }
 
        map.addAttribute("result",Integer.valueOf(result.substring(19,20)));
        map.addAttribute("files", fileNames);
        return "uploadfilesuccess";
    }
}
