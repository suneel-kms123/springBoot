package com.programspring.SpringBoot;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.kafka.ProducerExample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class SpringBootController {

	private static final Log LOG = LogFactory.getLog(SpringBootController.class);

	@Autowired
	private ProducerExample producerExampleService;

	@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "hello message")
	public @ResponseBody ResponseEntity<String> handleBoot() {
		LOG.debug("*********hello message***********");
		String message = "BOOT SPRING MVC";
		return ResponseEntity.ok().body(message);
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "send message")
	public ResponseEntity<String> sendKafkaMessage(@RequestParam String message) {
		LOG.debug("kafka send message" + message);
		ListenableFuture<SendResult<String, String>> future = producerExampleService.sendMessage(message, "example",
				"kafka-learn-sample");

		try {
			SendResult<String, String> result = future.get(10, TimeUnit.SECONDS);
			result.getProducerRecord().topic().equals("kafka-learn-sample");
			return ResponseEntity.ok().build();
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			LOG.error("Exception while getting result");
		}		

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
		
}
