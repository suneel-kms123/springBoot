package com.programspring.SpringBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SpringBootController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "hello message")  
	public ModelAndView handleBoot() {
		String message = "BOOT SPRING MVC";  
        return new ModelAndView("bootpage", "message", message); 
	}
	
	
}
