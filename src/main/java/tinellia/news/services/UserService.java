package tinellia.news.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinellia.news.domain.Users;
import tinellia.news.repository.UsersRepository;

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

    public Users createUser(String username, String password, String email, String authorities) {
        return usersRepository.save(Users.builder()
                .username(username)
                .password(password)
                .email(email)
                .authorities(authorities)
                .build());
    }

    public Users updateUser(UUID id, String username, String password, String email, String authorities) {
        return usersRepository.save(Users.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .authorities(authorities)
                .build());
    }

    public void deleteUser(UUID id) {
        usersRepository.delete(findUserByIdOrThrowException(id));
    }
}
