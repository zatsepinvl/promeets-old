package ru.unc6.promeets.model.service.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.unc6.promeets.model.entity.File;
import ru.unc6.promeets.model.entity.User;
import ru.unc6.promeets.model.repository.FileRepository;
import ru.unc6.promeets.model.repository.UserRepository;
import ru.unc6.promeets.model.service.entity.UserService;

/**
 * Created by Vladimir on 20.03.2016.
 */
@Service
public class UserServiceImp extends BaseServiceImpl<User, Long>
        implements UserService {

    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User save(User entity) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        if (entity.getImage() == null) {
            entity.setImage(new File());
        }
        fileRepository.save(entity.getImage());
        return userRepository.save(entity);
    }
}