package fr.insalyon.tc.springbootintro;

import fr.insalyon.tc.springbootintro.hello.GreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

	private final GreetingService greetingService;

	@Autowired
	public GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping("/greet")
	@ResponseBody
	public String greet(@RequestParam String name) {
		return greetingService.greet(name);
	}
}