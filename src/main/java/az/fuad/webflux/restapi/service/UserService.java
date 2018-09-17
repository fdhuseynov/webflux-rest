package az.fuad.webflux.restapi.service;

import az.fuad.webflux.restapi.model.User;
import az.fuad.webflux.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> retrieveUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(String id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setName(user.getName());
                    existingUser.setBirthDate(user.getBirthDate());
                    return userRepository.save(existingUser);
                });
    }

    public Mono<User> deleteUser(String id) {
        return userRepository.findById(id)
                .flatMap(existingUser -> userRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

}
