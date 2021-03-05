package com.programspring.SpringBoot;

import com.kafka.ProducerExample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;

@RestController
public class SpringBootController {

	private static final Log LOG = LogFactory.getLog(SpringBootController.class);

	@Autowired
	private ProducerExample producerExampleService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "hello message")  
	public ModelAndView handleBoot() {
		String message = "BOOT SPRING MVC";  
        return new ModelAndView("bootpage", "message", message); 
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "send message")  
	public void sendKafkaMessage(@RequestParam String message) {
		LOG.debug("kafka send message" + message);
		producerExampleService.sendMessage(message, "example", "kafka-learn-sample");
	}
		
}
