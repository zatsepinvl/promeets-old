/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.unc6.promeets.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.unc6.promeets.model.entity.Chat;
import ru.unc6.promeets.model.entity.Message;
import ru.unc6.promeets.model.entity.User;
import ru.unc6.promeets.model.service.entity.ChatService;
import ru.unc6.promeets.model.service.notify.MessageNotifyService;

/**
 *
 * @author MDay
 */

@RestController
public class ChatController 
{
    @Autowired
    ChatService chatService;
    
    @Autowired
    MessageNotifyService messageNotifyServicel;
    
     @RequestMapping(value = "api/chats/{id}", method = RequestMethod.GET)
    public ResponseEntity<Chat> getChatById(@PathVariable long id) 
    {
        Chat chat = chatService.getById(id);
        
        if (chat == null) 
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    
     @RequestMapping(value = "api/chats", method = RequestMethod.GET)
    public ResponseEntity<List<Chat>> getChats() 
    {
        List<Chat> chats = chatService.getAll();
        
        if (chats.isEmpty()) 
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "api/chats/", method = RequestMethod.POST)
    public ResponseEntity<Void> createChat(@RequestBody Chat chat) 
    { 
        if (chat == null) 
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        chatService.save(chat);
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "api/chats/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateChat(@RequestBody Chat chat, @PathVariable long id) 
    { 
        if (chatService.getById(id) == null) 
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        chat.setChatId(id);
        chatService.save(chat);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "api/chats/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> updateChat(@PathVariable long id) 
    { 
        if (chatService.getById(id) == null) 
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        chatService.delete(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "api/chats/{id}/messages/{pageId}", method = RequestMethod.GET)
    public List<Message> getChatMessages(@PathVariable long id, @PathVariable long pageId) 
    { 
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable page = new PageRequest((int) pageId, 20, sort);
        
        List<Message> messages = chatService.getMessagePageByChatId(id, page);
        
        return messages;
    }
    
    @RequestMapping(value = "api/chats/{id}/messages", method = RequestMethod.POST)
    public Message addChatMessage(@PathVariable long id, @RequestBody Message message) 
    { 
        message = chatService.addMessageByChatId(message, id);
        messageNotifyServicel.onCreate(message);
        return message;
    }
    
    @RequestMapping(value = "api/chats/{id}/users", method = RequestMethod.GET)
    public List<User> getChatUsers(@PathVariable long id) 
    { 
        List<User> users = chatService.getAllUsersByChatId(id);
        return users;
    }
}
