/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.unc6.promeets.model.service.notification.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.unc6.promeets.controller.AppSTOMPController;
import ru.unc6.promeets.model.entity.Chat;
import ru.unc6.promeets.model.entity.User;
import ru.unc6.promeets.model.repository.ChatRepository;
import ru.unc6.promeets.model.service.notification.ChatNotificationService;
import ru.unc6.promeets.model.service.notification.Notification;

/**
 *
 * @author MDay
 */

@Service
public class ChatNotificationServiceImpl implements ChatNotificationService
{
    @Autowired
    AppSTOMPController appSTOMPController;
    @Autowired
    ChatRepository chatRepository;

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
        List<User> users = (List<User>) chatRepository.getAllUsersByChatId(chat.getChatId());
        Notification notification = new Notification(chat.getClass(), action, chat.getChatId());
        for(User user : users){
            appSTOMPController.sendNotificationToUser(notification, user);
        }
    }
    
}