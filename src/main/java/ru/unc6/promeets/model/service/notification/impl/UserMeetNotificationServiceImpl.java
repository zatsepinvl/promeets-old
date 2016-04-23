package ru.unc6.promeets.model.service.notification.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.unc6.promeets.controller.AppSTOMPController;
import ru.unc6.promeets.model.entity.UserMeet;
import ru.unc6.promeets.model.service.notification.Notification;
import ru.unc6.promeets.model.service.notification.UserMeetNotificationService;

/**
 * Created by Vladimir on 11.04.2016.
 */
@Service
public class UserMeetNotificationServiceImpl extends BaseNotificationServiceImpl<UserMeet>
        implements UserMeetNotificationService {

    @Autowired
    private AppSTOMPController stompController;

    @Override
    protected void onAction(UserMeet entity, Notification.Action action) {
        stompController.sendNotificationToUser(new Notification(UserMeet.class, action, entity.getMeet().getMeetId()), entity.getUser());
    }
}
