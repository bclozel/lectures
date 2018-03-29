package fr.insalyon.tc.springbootintro.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleGreetingService implements GreetingService {

	private final CycleService cycleService;

	@Autowired
	public SimpleGreetingService(CycleService cycleService) {
		this.cycleService = cycleService;
	}

	@Override
	public String greet(String name) {
		return String.format("Hello, %s!", name);
	}

}