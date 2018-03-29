package fr.insalyon.tc.springbootintro.hello;

import org.springframework.stereotype.Service;

@Service
public class AnotherGreetingService implements GreetingService {

	@Override
	public String greet(String name) {
		return String.format("Bonjour, %s!", name);
	}
}
