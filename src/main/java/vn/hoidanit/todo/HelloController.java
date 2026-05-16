package vn.hoidanit.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.todo.entity.Todo;

@RestController
public class HelloController {
	@GetMapping("/")
	public ResponseEntity<String> index() {
//		return "Hello World from Spring Boot with video25";
		return ResponseEntity.ok().body("hello world");
	}

	@GetMapping("/hoidan")
	public ResponseEntity<Todo> hoidanit() {
		Todo test = new Todo("hoidanit", false);
		return ResponseEntity.ok().body(test);
	}

}
