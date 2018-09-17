package az.fuad.webflux.restapi.controller;

import az.fuad.webflux.restapi.model.User;
import az.fuad.webflux.restapi.repository.UserRepository;

import az.fuad.webflux.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userService.retrieveAllUsers();
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable  String id) {
        return userService.retrieveUserById(id)
                .map(savedUser -> ResponseEntity.ok(savedUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable String id,
                                                 @Valid @RequestBody User user) {
        return userService.updateUser(id, user)
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/users/{id}")
    public Mono<ResponseEntity<User>> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id)
                .map(deletedUser -> new ResponseEntity<>(deletedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
