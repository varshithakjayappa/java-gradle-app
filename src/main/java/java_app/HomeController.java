package java_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Mapping for the root URL (/)
    @GetMapping("/")
    public String home() {
        return "Welcome to the Java Application!";
    }

    // Personalized greeting (using query parameter for dynamic name)
    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "Guest") String name) {
        return "Hello, " + name + "! Welcome to Spring Boot!";
    }

    // Optional: Additional endpoint for testing
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }
}
