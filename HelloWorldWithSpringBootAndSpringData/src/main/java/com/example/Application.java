package com.example;

import com.example.model.Message;
import com.example.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    MessageRepository messageRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    CommandLineRunner run(){
        return (x) -> {
            Message message = new Message();
            message.setText("Hello World");

            messageRepository.save(message);

            Iterable<Message> messages = messageRepository.findAll();
            messages.forEach(m -> System.out.println(m.getText()));

        };
    }
}
