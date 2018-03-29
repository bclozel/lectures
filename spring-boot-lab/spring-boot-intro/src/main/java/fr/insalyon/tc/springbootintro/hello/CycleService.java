package fr.insalyon.tc.springbootintro.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CycleService {

	private final GreetingService greetingService;

	@Autowired
	public CycleService(GreetingService greetingService) {
		this.greetingService = greetingService;
	}
}