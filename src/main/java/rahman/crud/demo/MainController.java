package rahman.crud.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    // Home Page
    @GetMapping("")
    public String homePage() {
        return "index";
    }

}
