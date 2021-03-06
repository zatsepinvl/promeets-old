package com.promeets.model.service.entity.impl;

import com.promeets.model.service.entity.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promeets.model.entity.MeetTask;
import com.promeets.model.repository.TaskRepository;
import com.promeets.model.service.notification.MeetNotificationService;
import com.promeets.model.service.notification.TaskNotificationService;

import java.util.List;

/**
 * Created by Vladimir on 06.04.2016.
 */
@Service
public class TaskServiceImpl extends BaseNotifiedServiceImpl<MeetTask, Long> implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    private MeetNotificationService meetNotificationService;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, TaskNotificationService notificationService) {
        super(repository, notificationService);
        this.taskRepository = repository;
    }

    @Override
    public List<MeetTask> getTasksByMeetId(long meetId) {
        return (List<MeetTask>) taskRepository.getMeetTasksByMeetId(meetId);
    }

    @Override
    public void deleteTasksByMeetId(long meetId) {
        taskRepository.deleteTasksByMeetId(meetId);
    }

    @Override
    public MeetTask create(MeetTask entity) {
        entity = super.create(entity);
        meetNotificationService.onCreate(entity.getMeet());
        return entity;
    }

    @Override
    public MeetTask update(MeetTask entity) {
        entity = super.update(entity);
        meetNotificationService.onCreate(entity.getMeet());
        return entity;
    }
}
