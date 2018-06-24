package de.exb.platform.cloud.fileclient.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fileapi")
public class FileApiProperties {
	
	private String fileUri;
	private String fileMetadataUri;

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getFileMetadataUri() {
		return fileMetadataUri;
	}

	public void setFileMetadataUri(String fileMetadataUri) {
		this.fileMetadataUri = fileMetadataUri;
	}

}
