package service;

import domain.entities.User;
import domain.models.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import repository.UserRepository;
import util.Mapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final Mapper mapper;

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(Mapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.mapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean loginUser(UserServiceModel userServiceModel) {
        if (this.userRepository.findByUsernameAndPassword(userServiceModel.getUsername(), DigestUtils.sha256Hex(userServiceModel.getPassword())) != null) {
            return true;
        }

        return false;
    }
}
