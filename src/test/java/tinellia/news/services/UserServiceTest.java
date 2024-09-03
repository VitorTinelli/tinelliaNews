package tinellia.news.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tinellia.news.domain.Users;
import tinellia.news.repository.UsersRepository;
import tinellia.news.requests.UserCreationRequest;
import tinellia.news.requests.UserUpdateRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    Users user;
    UserCreationRequest userCreationRequest;
    UserUpdateRequest userUpdateRequest;

    @InjectMocks
    private UserService userService;

    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        user = Users.builder()
                .id(UUID.randomUUID())
                .username("test")
                .password("test")
                .email("teste@gmail.com")
                .authorities("test").build();
        userCreationRequest = UserCreationRequest.builder()
                .username("test")
                .password("test")
                .email("teste@gmail.com")
                .authorities("test").build();
        userUpdateRequest = UserUpdateRequest.builder()
                .id(UUID.randomUUID())
                .username("test")
                .password("test")
                .email("teste@gmail.com")
                .authorities("test").build();
    }

    @Test
    void getAllUsersShouldReturnAllUsers() {
        when(usersRepository.findAll()).thenReturn(List.of(user));
        List<Users> usersFound = userService.getAllUsers();
        assert (usersFound.size() == 1);
        verify(usersRepository).findAll();
    }

    @Test
    void findUserByUsernameShouldReturnUser() {
        when(usersRepository.findByUsername("test")).thenReturn(user);
        Users userFound = userService.findUserByUsername("test");
        assert (userFound.getUsername().equals("test"));
        verify(usersRepository).findByUsername("test");
    }

    @Test
    void findUserByIdOrThrowExceptionShouldReturnUser() {
        when(usersRepository.findById((user.getId()))).thenReturn(Optional.of(user));
        Users userFound = userService.findUserByIdOrThrowException(user.getId());
        assert (userFound.getId().equals(user.getId()));
        verify(usersRepository).findById(user.getId());
    }

    @Test
    void findUserByIdOrThrowExceptionShouldThrowException() {
        when(usersRepository.findById((user.getId()))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> userService.findUserByIdOrThrowException(user.getId()));
        verify(usersRepository).findById(user.getId());
    }

    @Test
    void createUser() {
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        Users userCreated = userService.createUser(userCreationRequest);
        Assertions.assertEquals(userCreated, user);
    }

    @Test
    void updateUser() {
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        Users userUpdated = userService.updateUser(userUpdateRequest);
        Assertions.assertEquals(userUpdated, user);
    }

    @Test
    void deleteUser() {
        when(usersRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Assertions.assertDoesNotThrow(() -> userService.deleteUser(user.getId()));
        verify(usersRepository).delete(user);
    }
}