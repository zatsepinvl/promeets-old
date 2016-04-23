package ru.unc6.promeets.model.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.unc6.promeets.model.entity.MeetNote;
import ru.unc6.promeets.model.repository.NoteRepository;
import ru.unc6.promeets.model.service.entity.NoteService;
import ru.unc6.promeets.model.service.notification.NoteNotificationService;

/**
 * Created by Vladimir on 30.03.2016.
 */
@Component
public class NoteServiceImpl extends BaseNotificatedServiceImpl<MeetNote, Long>
        implements NoteService {
    @Autowired
    public NoteServiceImpl(NoteRepository repository, NoteNotificationService notificationService) {
        super(repository,notificationService);
    }
}
