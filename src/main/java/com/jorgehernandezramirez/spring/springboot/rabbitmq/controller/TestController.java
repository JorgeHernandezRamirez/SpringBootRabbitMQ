package com.jorgehernandezramirez.spring.springboot.rabbitmq.controller;

import com.jorgehernandezramirez.spring.springboot.rabbitmq.config.RabbitConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    private static final String MESSAGE = "Message to RabbitMQ!";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public TestController(){
        //Para Spring
    }

    @RequestMapping("/")
    public String doSendMessageToRabbitMQ() {
        rabbitTemplate.convertAndSend(RabbitConfiguration.QUEUE_NAME, MESSAGE);
        return "Alive!";
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_NAME)
    public void onMessageFromRabbitMQ(final String messageFromRabbitMQ){
        LOGGER.info("{}", messageFromRabbitMQ);
    }
}
