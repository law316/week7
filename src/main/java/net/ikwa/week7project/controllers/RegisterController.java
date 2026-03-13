package net.ikwa.week7project.controllers;

import net.ikwa.week7project.model.UserModel;
import net.ikwa.week7project.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users") // 🔧 API VERSIONING ADDED
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // 1️⃣ CREATE USER
    @PostMapping
    public ResponseEntity<UserModel> registerUser(@Valid @RequestBody UserModel user) {

        UserModel saved = userService.createUser(user);

        return ResponseEntity
                .status(201) // 🔧 Proper REST status code
                .body(saved);
    }

    // 2️⃣ GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers(){

        List<UserModel> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    // 3️⃣ GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id){

        UserModel user = userService.getUserById(id);

        return ResponseEntity.ok(user);
    }

    // 4️⃣ UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserModel user){

        UserModel updated = userService.updateUser(id, user);

        return ResponseEntity.ok(updated);
    }

    // 5️⃣ DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.noContent().build(); // 🔧 Correct delete response
    }
}