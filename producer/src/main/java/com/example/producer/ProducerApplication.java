package com.example.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(ProducerChannels.class)
@RestController
@SpringBootApplication
public class ProducerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProducerApplication.class);

	@Autowired
	private ProducerChannels channels;

	@PostMapping("/player")
	@SendTo(ProducerChannels.OUTPUT)
	public String publish(@RequestBody Player player){
		logger.info("Got new message from http: " + player);
		channels.consumer().send(MessageBuilder.withPayload(new Player(player.getTeam(), player.getName())).build());
		return "Successfully sent player: " + player;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}

interface ProducerChannels {
	String OUTPUT = "consumer";

	@Output(ProducerChannels.OUTPUT)
	MessageChannel consumer();
}

