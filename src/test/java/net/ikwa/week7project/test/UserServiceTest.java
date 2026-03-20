package net.ikwa.week7project.test;

import net.ikwa.week7project.model.UserModel;
import net.ikwa.week7project.repo.UserRepo;
import net.ikwa.week7project.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_shouldSaveUser() {
        UserModel user = new UserModel("John", "john@mail.com", "USER");
        when(userRepo.save(user)).thenReturn(user);

        UserModel result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(userRepo).save(user);
    }

    @Test
    void getAllUsers_shouldReturnList() {
        when(userRepo.findAll()).thenReturn(List.of(
                new UserModel("A", "a@mail.com", "USER"),
                new UserModel("B", "b@mail.com", "ADMIN")
        ));

        List<UserModel> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepo).findAll();
    }

    @Test
    void getUserById_shouldReturnUser() {
        UserModel user = new UserModel("Test", "test@mail.com", "USER");
        user.setId(1L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        UserModel result = userService.getUserById(1L);

        assertEquals("Test", result.getName());
    }

    @Test
    void getUserById_shouldThrowIfNotFound() {
        when(userRepo.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.getUserById(1L));

        assertTrue(ex.getMessage().contains("User not found"));
    }

    @Test
    void updateUser_shouldUpdateFields() {
        UserModel existing = new UserModel("Old", "old@mail.com", "USER");
        existing.setId(1L);

        UserModel update = new UserModel("New", "new@mail.com", "ADMIN");

        when(userRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepo.save(existing)).thenReturn(existing);

        UserModel result = userService.updateUser(1L, update);

        assertEquals("New", result.getName());
        assertEquals("ADMIN", result.getRole());
    }

    @Test
    void deleteUser_shouldDelete() {
        UserModel user = new UserModel("Test", "test@mail.com", "USER");
        user.setId(1L);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepo).delete(user);
    }
}