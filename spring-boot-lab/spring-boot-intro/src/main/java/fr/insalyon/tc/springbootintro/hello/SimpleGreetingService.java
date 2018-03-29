package fr.insalyon.tc.springbootintro.hello;

import org.springframework.stereotype.Service;

@Service
public class SimpleGreetingService implements GreetingService {

	@Override
	public String greet(String name) {
		return String.format("Hello, %s!", name);
	}

}