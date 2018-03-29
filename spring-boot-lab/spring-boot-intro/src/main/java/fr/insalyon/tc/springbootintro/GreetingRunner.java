package fr.insalyon.tc.springbootintro;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GreetingRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String name = "Spring";
		String greeting = String.format("Hello, %s!", name);
		System.out.println(greeting);
	}
}