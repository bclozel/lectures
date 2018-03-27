package fr.insalyon.tc.springbootintro;

import fr.insalyon.tc.springbootintro.hello.GreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GreetingRunner implements ApplicationRunner {

	private final GreetingService greetingService;

	@Autowired
	public GreetingRunner(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String name = "Spring";
		String greeting = this.greetingService.greet(name);
		System.out.println(greeting);
	}
}