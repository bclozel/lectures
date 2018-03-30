package fr.insalyon.tc.springboothol;

import java.net.URI;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortURLController.class)
public class ShortURLControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ShortURLService shortURLService;

	@Test
	public void shouldShortenURL() throws Exception {
		URI uri = URI.create("http://example.org");
		ShortURL shortUrl = new ShortURL(uri, "example");
		given(this.shortURLService.shortenURL(eq(uri))).willReturn(shortUrl);
		this.mvc.perform(post("/").param("uri", "http://example.org"))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", Matchers.endsWith("/example")));
	}

	@Test
	public void shouldRedirectToURL() throws Exception {
		URI uri = URI.create("http://example.org");
		ShortURL shortUrl = new ShortURL(uri, "example");
		given(this.shortURLService.expandShortCode(eq("example")))
				.willReturn(shortUrl);
		this.mvc.perform(get("/example"))
				.andExpect(status().isPermanentRedirect())
				.andExpect(header().string("Location", "http://example.org"));
	}

	@Test
	public void shouldReturnNotFoundForMissingShortURLs() throws Exception {
		given(this.shortURLService.expandShortCode(eq("example")))
				.willThrow(new ShortCodeNotFoundException("example"));
		this.mvc.perform(get("/example"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldShowJSONInfo() throws Exception {
		URI uri = URI.create("http://example.org");
		ShortURL shortUrl = new ShortURL(uri, "example");
		given(this.shortURLService.expandShortCode("example")).willReturn(shortUrl);
		this.mvc.perform(get("/example/info").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.shortCode").value("example"))
				.andExpect(jsonPath("$.uri").value("http://example.org"));
	}

}