package com.example.Reto3.repository.crudRepository;

import com.example.Reto3.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message,Integer> {

}