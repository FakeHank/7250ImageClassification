package backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorld {
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		String message = "<center>Hank最帅了！！</center>";
		return new ModelAndView("welcome", "message", message);
	}
}
