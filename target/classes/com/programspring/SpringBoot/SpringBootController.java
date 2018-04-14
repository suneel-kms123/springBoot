package com.programspring.SpringBoot;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringBootController {

	@RequestMapping("/hello")  
	public ModelAndView handleBoot() {
		String message = "BOOT SPRING MVC";  
        return new ModelAndView("bootpage", "message", message); 
	}
	
	
}
