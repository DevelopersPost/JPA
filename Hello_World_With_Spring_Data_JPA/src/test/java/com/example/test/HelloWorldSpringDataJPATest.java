package com.example.test;

import com.example.configuration.SpringDataConfiguration;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class HelloWorldSpringDataJPATest {

    @Autowired
    MessageRepository messageRepository;

    @Test
    public void storeAndReadMessages(){
        Message message = new Message();
        message.setText("Hello World");
        message.setId(1L);

        messageRepository.save(message);

        List<Message> all = (List<Message>) messageRepository.findAll();

        all.forEach(message1 -> System.out.println(message1.getText()));
    }
}
