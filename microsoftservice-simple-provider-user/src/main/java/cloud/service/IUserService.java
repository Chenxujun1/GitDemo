package cloud.service;

import cloud.entity.User;

public interface IUserService {
    User getUserById(String id);
}
