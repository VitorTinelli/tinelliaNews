package tinellia.news.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tinellia.news.domain.Users;
import tinellia.news.requests.UserCreationRequest;
import tinellia.news.requests.UserUpdateRequest;
import tinellia.news.services.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/find/{username}")
    public Users findUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("/{id}")
    public Users findUserByIdOrThrowException(@PathVariable UUID id) {
        return userService.findUserByIdOrThrowException(id);
    }

    @PostMapping
    public Users createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        return userService.createUser(userCreationRequest.getUsername(), userCreationRequest.getPassword(), userCreationRequest.getEmail(), userCreationRequest.getAuthorities());
    }

    @PutMapping("/{id}")
    public Users updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userUpdateRequest.getId(), userUpdateRequest.getUsername(), userUpdateRequest.getPassword(), userUpdateRequest.getEmail(), userUpdateRequest.getAuthorities());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
