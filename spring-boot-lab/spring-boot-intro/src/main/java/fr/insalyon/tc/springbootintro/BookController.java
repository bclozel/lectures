package fr.insalyon.tc.springbootintro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

	@GetMapping("/{isbn}")
	public ResponseEntity<Book> findBook(@PathVariable String isbn) {
		Book algoDesign = new Book();
		algoDesign.setIsbn(isbn);
		algoDesign.setAuthor("Steven Skiena");
		algoDesign.setTitle("The Algorithm Design Manual");
		algoDesign.setVersion("v1");
		return ResponseEntity
				.ok()
				.eTag(algoDesign.getVersion())
				.body(algoDesign);
	}

}