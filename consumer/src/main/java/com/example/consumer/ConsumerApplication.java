package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding(ConsumerChannels.class)
public class ConsumerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

	@StreamListener(ConsumerChannels.INPUT)
	public void handle(Player player){
		logger.info("new message from RabbitMQ broker: " + player.toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}

interface ConsumerChannels {
	String INPUT = "producer";

	@Input
	SubscribableChannel producer();
}



