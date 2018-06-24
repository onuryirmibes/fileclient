package de.exb.platform.cloud.fileclient.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	@Autowired
	private FileApiService fileApiService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileUploads(Model model, @RequestParam("file") MultipartFile file) {
		
		Map<Object, Object> result = fileApiService.upload(file);
		String fileId = (String) result.get("id");
	
		return "redirect:/";
	}
}
