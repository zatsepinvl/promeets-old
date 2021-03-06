package com.promeets.model.service.entity.impl;

import com.promeets.model.entity.MeetNote;
import com.promeets.model.repository.NoteRepository;
import com.promeets.model.service.entity.NoteService;
import com.promeets.model.service.notification.MeetNotificationService;
import com.promeets.model.service.notification.NoteNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vladimir on 30.03.2016.
 */
@Component
public class NoteServiceImpl extends BaseNotifiedServiceImpl<MeetNote, Long>
        implements NoteService {
    private NoteRepository noteRepository;

    @Autowired
    private MeetNotificationService meetNotificationService;

    @Autowired
    public NoteServiceImpl(NoteRepository repository, NoteNotificationService notificationService) {
        super(repository, notificationService);
        this.noteRepository = repository;
    }

    @Override
    public List<MeetNote> getNotesByMeetId(long meetId) {
        return (List<MeetNote>) noteRepository.getMeetNotesByMeetId(meetId);
    }

    @Override
    public void deleteNotesByMeetId(long meetId) {
        noteRepository.deleteNotesByMeetId(meetId);
    }

    @Override
    public MeetNote create(MeetNote entity) {
        entity = super.create(entity);
        meetNotificationService.onUpdate(entity.getMeet());
        return entity;
    }

    @Override
    public MeetNote update(MeetNote entity) {
        entity = super.update(entity);
        meetNotificationService.onUpdate(entity.getMeet());
        return entity;
    }
}
