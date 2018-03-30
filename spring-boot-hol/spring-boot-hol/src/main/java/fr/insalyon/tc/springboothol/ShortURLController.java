package fr.insalyon.tc.springboothol;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@Controller
public class ShortURLController {

	private final ShortURLService shortURLService;

	@Autowired
	public ShortURLController(ShortURLService shortURLService) {
		this.shortURLService = shortURLService;
	}

	@PostMapping("/")
	public ResponseEntity shortenURL(@RequestParam URI uri) {
		ShortURL shortURL = this.shortURLService.shortenURL(uri);
		URI shortenedURL = URI.create("http://localhost:8080/" + shortURL.getShortCode());
		// the host information is hard coded here for simplicity
		// but you can do better with MvcUriComponentsBuilder::fromMethodCall
		return ResponseEntity.created(shortenedURL).build();
	}

	@GetMapping("/{shortCode}")
	public ResponseEntity expandURL(@PathVariable String shortCode) {
		ShortURL shortURL = this.shortURLService.expandShortCode(shortCode);
		return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
				.location(shortURL.getUri()).build();
	}

	@GetMapping("/{shortCode}/info")
	@ResponseBody
	public ShortURL fetchShortURLInfo(@PathVariable String shortCode) {
		return this.shortURLService.expandShortCode(shortCode);
	}

}
