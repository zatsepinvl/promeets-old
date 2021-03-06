/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.promeets.model.service.notification.impl;

import java.util.List;

import com.promeets.model.repository.UserRepository;
import com.promeets.model.service.notification.ChatNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.promeets.controller.StompNotificationController;
import com.promeets.model.entity.Chat;
import com.promeets.model.entity.User;
import com.promeets.model.service.notification.Notification;

@Service
public class ChatNotificationServiceImpl implements ChatNotificationService
{
    @Autowired
    StompNotificationController notificationController;
    @Autowired
    UserRepository userRepository;

    @Async
    @Override
    public void onCreate(Chat chat) 
    {
        onAction(chat, Notification.Action.CREATE);
    }

    @Async
    @Override
    public void onUpdate(Chat chat) 
    {
        onAction(chat, Notification.Action.UPDATE);
    }

    @Async
    @Override
    public void onDelete(Chat chat) 
    {
        onAction(chat, Notification.Action.DELETE);
    }
    
    private void onAction(Chat chat, Notification.Action action) 
    {
        List<User> users = (List<User>) userRepository.getUsersByChatId(chat.getChatId());
        Notification notification = new Notification(chat.getClass(), action, chat.getChatId());
        for(User user : users){
            notificationController.notifyUser(notification, user);
        }
    }
    
}
