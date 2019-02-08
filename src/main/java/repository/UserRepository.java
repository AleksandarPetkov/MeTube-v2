package repository;

import domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
}
