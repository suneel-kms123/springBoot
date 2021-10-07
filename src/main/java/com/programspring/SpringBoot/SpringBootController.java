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
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

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


	@PostMapping(value = "/uploadVideo/{username}")
	@ResponseBody
	@ApiOperation("upload Video")
	public ResponseEntity<Object> uploadVideo(@RequestParam("File") MultipartFile file,
									  @PathVariable("username") String username) {
		// find user exist
		// save the file in database against the userName
		// push notification to friends and subscribers - push event to kafka
		// reply success message
		// Ui should refresh and get the video on TL
		return ResponseEntity.ok().build();
	}


}
