package fr.insalyon.tc.springboothol;

import java.net.URI;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RepositorySample implements ApplicationRunner {

	private final Logger logger = LoggerFactory.getLogger(RepositorySample.class);

	private final ShortURLRepository repository;

	@Autowired
	public RepositorySample(ShortURLRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 1) create a few ShortURL objects
		ShortURL spr = new ShortURL(URI.create("https://spring.io"), "SPR");
		ShortURL boot = new ShortURL(URI.create("https://github.com/spring-projects/spring-boot"), "boot");
		ShortURL rest = new ShortURL(URI.create("https://spring.io/guides/gs/rest-service/"), "rest");
		// 2) persist them using the repository
		this.repository.saveAll(Arrays.asList(spr, boot, rest));
		// 3) find all ShortURLs in the database
		// 4) log them using the logger
		this.repository.findAll().forEach(s -> logger.info(s.toString()));
	}

}