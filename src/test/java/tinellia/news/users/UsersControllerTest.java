package tinellia.news.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {
    Users user;

    @InjectMocks
    private UsersController usersController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = Users.builder()
                .id(UUID.randomUUID())
                .username("test")
                .password("test")
                .email("users@teste.com")
                .authorities("test").build();
    }

    @Test
    void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(List.of(user));
        List<Users> usersFound = usersController.getAllUsers();
        assertEquals(1, usersFound.size());
        verify(userService).getAllUsers();
    }

    @Test
    void findUserByUsername() {
        when(userService.findUserByUsername("test")).thenReturn(user);
        Users userFound = usersController.findUserByUsername("test");
        assertEquals("test", userFound.getUsername());
        verify(userService).findUserByUsername("test");
    }

    @Test
    void findUserByIdOrThrowException() {
        when(userService.findUserByIdOrThrowException(user.getId())).thenReturn(user);
        Users userFound = usersController.findUserByIdOrThrowException(user.getId());
        assertEquals(user.getId(), userFound.getId());
        verify(userService).findUserByIdOrThrowException(user.getId());
    }

    @Test
    void createUser() {
        when(userService.createUser(any())).thenReturn(user);
        Users userCreated = usersController.createUser(any());
        assertEquals(user, userCreated);
        verify(userService).createUser(any());
    }

    @Test
    void updateUser() {
        when(userService.updateUser(any())).thenReturn(user);
        Users userUpdated = usersController.updateUser(any());
        assertEquals(user, userUpdated);
        verify(userService).updateUser(any());
    }

    @Test
    void deleteUser() {
        Assertions.assertDoesNotThrow(() -> usersController.deleteUser(user.getId()));
        verify(userService).deleteUser(user.getId());
    }
}