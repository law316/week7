package net.ikwa.week7project.test;

import net.ikwa.week7project.controllers.RegisterController;
import net.ikwa.week7project.model.UserModel;
import net.ikwa.week7project.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    private UserService userService = mock(UserService.class);

    private RegisterController controller =
            new RegisterController(userService);

    @Test
    void registerUser_shouldReturnCreated() {
        UserModel user = new UserModel("Emma", "emma@mail.com", "USER");
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<UserModel> response = controller.registerUser(user);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Emma", response.getBody().getName());
    }

    @Test
    void getUsers_shouldReturnList() {
        when(userService.getAllUsers()).thenReturn(List.of(
                new UserModel("A", "a@mail.com", "USER")
        ));

        ResponseEntity<List<UserModel>> response = controller.getUsers();

        assertEquals(1, response.getBody().size());
    }

    @Test
    void getUser_shouldReturnUser() {
        UserModel user = new UserModel("John", "john@mail.com", "USER");

        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<UserModel> response = controller.getUser(1L);

        assertEquals("John", response.getBody().getName());
    }

    @Test
    void deleteUser_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteUser(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService).deleteUser(1L);
    }
}