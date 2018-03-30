package fr.insalyon.tc.springboothol;


import java.net.URI;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShortURLServiceTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock
	private ShortURLRepository repository;

	private ShortURLService shortURLService;

	@Before
	public void setup() {
		initMocks(this);
		this.shortURLService = new ShortURLService(this.repository);
		doAnswer(invocation -> invocation.getArgument(0)).when(this.repository).save(any());
	}

	@Test
	public void canShortenURLs() {
		URI uri = URI.create("http://example.org/resource");
		ShortURL shortURL = this.shortURLService.shortenURL(uri);
		assertThat(shortURL.getShortCode()).isNotBlank();
		assertThat(shortURL.getUri()).isEqualTo(uri);
		verify(this.repository, times(1)).save(any());
	}

	@Test
	public void canExpandShortCodes() {
		URI uri = URI.create("http://example.org/resource");
		ShortURL shortURL = new ShortURL(uri, "spring");
		given(this.repository.findByShortCode("spring")).willReturn(Optional.of(shortURL));
		ShortURL result = this.shortURLService.expandShortCode("spring");
		assertThat(result.getUri()).isEqualTo(uri);
		assertThat(result.getShortCode()).isEqualTo("spring");
		verify(this.repository, times(1)).findByShortCode(eq("spring"));
	}

	@Test
	public void unknownShortCode() {
		given(this.repository.findByShortCode("spring")).willReturn(Optional.empty());
		this.thrown.expect(ShortCodeNotFoundException.class);
		this.thrown.expect(Matchers.hasProperty("shortCode", Matchers.equalTo("spring")));
		ShortURL result = this.shortURLService.expandShortCode("spring");
	}

}