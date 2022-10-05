package com.example.Reto3.service;

import com.example.Reto3.entities.Message;
import com.example.Reto3.repository.crudRepository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    public Optional<Message> getProduct(int id){
        return messageRepository.getMessage(id);
    }
    public Message save(Message message){
        if(message.getId()==null){
            return messageRepository.save(message);
        }else{
            Optional<Message> optionalGame = messageRepository.getMessage(message.getId());
            if(optionalGame.isPresent()){

                return message;
            }else{
                return messageRepository.save(message);
            }
        }
    }
    public Message update(Message message){
        if(message.getId()!=null){
            Optional<Message> optionalMessage = messageRepository.getMessage(message.getId());
            if(optionalMessage.isPresent()){
                if(message.getMessageText()!=null){
                    optionalMessage.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(optionalMessage.get());
                return optionalMessage.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Message> optionalMessage = messageRepository.getMessage(id);
        if(optionalMessage.isPresent()){
            messageRepository.delete(optionalMessage.get());
            flag=true;
        }
        return flag;

    }


}
