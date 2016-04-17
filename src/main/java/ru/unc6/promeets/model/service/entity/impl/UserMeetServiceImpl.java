package ru.unc6.promeets.model.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.unc6.promeets.model.entity.Meet;
import ru.unc6.promeets.model.entity.User;
import ru.unc6.promeets.model.entity.UserMeet;
import ru.unc6.promeets.model.entity.UserMeetPK;
import ru.unc6.promeets.model.repository.UserGroupRepository;
import ru.unc6.promeets.model.repository.UserMeetRepository;
import ru.unc6.promeets.model.service.entity.UserMeetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMeetServiceImpl extends BaseServiceImpl<UserMeet, UserMeetPK>
        implements UserMeetService {

    private UserMeetRepository userMeetRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    public UserMeetServiceImpl(UserMeetRepository repository) {
        super(repository);
        this.userMeetRepository = repository;
    }

    @Override
    public List<UserMeet> getUserMeetsByMeetId(long id) {
        return (List<UserMeet>) userMeetRepository.getUserMeetsByMeetId(id);
    }

    @Override
    public List<UserMeet> getUserMeetsByUserId(long id) {
        return (List<UserMeet>) userMeetRepository.getUserMeetsByUserId(id);
    }

    @Override
    public List<UserMeet> getNotViewedMeetsByUserId(long id) {
        return (List<UserMeet>) userMeetRepository.getNotViewedMeetsByUserId(id);
    }

    @Override
    public void createUserMeetsByMeet(Meet meet) {
        List<UserMeet> userMeets = new ArrayList<>();
        long adminId = meet.getAdmin().getUserId();
        for (User user : userGroupRepository.getUsersByGroupId(meet.getGroup().getGroupId())) {
            UserMeetPK userMeetPK = new UserMeetPK();
            userMeetPK.setUser(user);
            userMeetPK.setMeet(meet);
            UserMeet userMeet = new UserMeet();
            userMeet.setUserMeetPK(userMeetPK);
            userMeet.setViewed(user.getUserId() == adminId);
            userMeets.add(userMeet);
        }
        userMeetRepository.save(userMeets);
    }

    @Override
    public void deleteUserMeetsByMeetId(long meetId) {
        userMeetRepository.deleteUserMeetsByMeetId(meetId);
    }
}