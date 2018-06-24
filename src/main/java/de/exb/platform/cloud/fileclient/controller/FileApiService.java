package de.exb.platform.cloud.fileclient.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import de.exb.platform.cloud.fileclient.exception.CallServiceException;
import de.exb.platform.cloud.fileclient.property.FileApiProperties;


@Component
public class FileApiService {

	private static final Logger LOGGER = LogManager.getLogger(FileApiService.class);

	@Autowired
	private FileApiProperties properties;

	private HttpHeaders setHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setExpires(0);
		headers.setCacheControl("private, no-store, max-age=0");
		return headers;
	}

	public Map<Object, Object> upload(MultipartFile file) {

		HttpEntity<MultipartFile> request = new HttpEntity<>(file, setHeader());
		RestTemplate restTemplate = new RestTemplate();
		String url = properties.getFileUri() + "/uploadFile";
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
		} catch (Exception ex) {
			logAndThrowCallServiceException(url, ex);
		}

		if (response == null) {
			throw new CallServiceException("File Service returned empty");
		}

		return response.getBody();
	}

	private void logAndThrowCallServiceException(String url, Exception ex) {
		LOGGER.info("Unsuccessful service call. URL: " + url, ex);
		throw new CallServiceException("Can not get User Info from service");
	}

}
