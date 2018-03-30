package fr.insalyon.tc.springboothol;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShortURL {

	@Id
	private String id;

	private URI uri;

	@Indexed(unique = true)
	private String shortCode;

	private LocalDateTime creationDate;

	public ShortURL() {
	}

	public ShortURL(URI uri, String shortCode) {
		this.uri = uri;
		this.shortCode = shortCode;
		this.creationDate = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "ShortURL{" +
				"id='" + id + '\'' +
				", uri=" + uri +
				", shortCode='" + shortCode + '\'' +
				", creationDate=" + creationDate +
				'}';
	}
}