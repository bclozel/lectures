package fr.insalyon.tc.springbootintro.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SimpleGreetingService implements GreetingService {

	@Override
	public String greet(String name) {
		return String.format("Hello, %s!", name);
	}
}
