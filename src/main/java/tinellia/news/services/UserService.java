package tinellia.news.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinellia.news.domain.Users;
import tinellia.news.repository.UsersRepository;
import tinellia.news.requests.UserCreationRequest;
import tinellia.news.requests.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users findUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Users findUserByIdOrThrowException(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users createUser(UserCreationRequest userCreationRequest) {
        return usersRepository.save(Users.builder()
                .username(userCreationRequest.getUsername())
                .password(userCreationRequest.getPassword())
                .email(userCreationRequest.getEmail())
                .authorities(userCreationRequest.getAuthorities())
                .build());
    }

    public Users updateUser(UserUpdateRequest userUpdateRequest) {
        return usersRepository.save(Users.builder()
                .id(userUpdateRequest.getId())
                .username(userUpdateRequest.getUsername())
                .password(userUpdateRequest.getPassword())
                .email(userUpdateRequest.getEmail())
                .authorities(userUpdateRequest.getAuthorities())
                .build());
    }

    public void deleteUser(UUID id) {
        usersRepository.delete(findUserByIdOrThrowException(id));
    }
}
