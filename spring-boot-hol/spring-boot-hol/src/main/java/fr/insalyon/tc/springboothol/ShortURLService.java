package fr.insalyon.tc.springboothol;

import java.net.URI;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ShortURLService {

	private static final String SPACE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";

	private static final int BASE = SPACE.length();

	private final Random random = new Random();

	private final ShortURLRepository shortURLRepository;

	@Autowired
	public ShortURLService(ShortURLRepository shortURLRepository) {
		this.shortURLRepository = shortURLRepository;
	}

	/**
	 * Create a new {@link ShortURL} using the given URI and save it
	 * in a datastore. The {@code shortCode} used to identify this
	 * URI should be randomly selected.
	 * @param uri the URI to shorten
	 * @return the shortened URL information
	 */
	public ShortURL shortenURL(URI uri) {
		// TODO: create a ShortURL using a random positive integer
		// use java.util.Random and java.util.Math for that
		int randomNumber = Math.abs(this.random.nextInt());
		String shortCode = encode(randomNumber);
		ShortURL shortURL = new ShortURL(uri, shortCode);
		// then persist that ShortURL into our database
		return this.shortURLRepository.save(shortURL);
	}

	/**
	 * Return the {@link ShortURL} information for the given
	 * {code shortCode}, if it exists in the datastore.
	 * @param shortCode the shortened URL code to look for
	 * @return the ShortURL information for the given shortCode
	 * @throws ShortCodeNotFoundException if no ShortURL could be found
	 * for the given shortCode
	 */
	public ShortURL expandShortCode(String shortCode) {
		// TODO: find a ShortURL by its shortcode in the database and return it
		// if there's none, throw a ShortCodeNotFoundException
		return this.shortURLRepository.findByShortCode(shortCode)
				.orElseThrow(() -> new ShortCodeNotFoundException(shortCode));
	}

	private static String encode(int num) {
		Assert.isTrue(num > 0, "Number must be positive");
		StringBuilder str = new StringBuilder();
		while (num > 0) {
			str.insert(0, SPACE.charAt(num % BASE));
			num = num / BASE;
		}
		return str.toString();
	}

}