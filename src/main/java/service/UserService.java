package service;

import domain.models.service.UserServiceModel;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);
}
