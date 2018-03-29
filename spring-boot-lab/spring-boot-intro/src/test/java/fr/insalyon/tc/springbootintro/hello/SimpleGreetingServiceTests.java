package fr.insalyon.tc.springbootintro.hello;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleGreetingServiceTests {

	@Test
	public void testGreeting() {
		SimpleGreetingService greetingService = new SimpleGreetingService();
		String message = greetingService.greet("Spring");
		assertThat(message).isEqualTo("Hello, Spring!");
	}
}