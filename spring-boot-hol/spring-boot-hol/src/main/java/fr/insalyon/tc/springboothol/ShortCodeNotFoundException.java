package fr.insalyon.tc.springboothol;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShortCodeNotFoundException extends RuntimeException {

	private final String shortCode;

	public ShortCodeNotFoundException(String shortCode) {
		super(String.format("Could not find ShortURL for [%s]", shortCode));
		this.shortCode = shortCode;
	}

	public String getShortCode() {
		return shortCode;
	}
}