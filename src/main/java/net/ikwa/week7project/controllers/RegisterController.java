package net.ikwa.week7project.controllers;

import net.ikwa.week7project.model.UserModel;
import net.ikwa.week7project.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // 1 CREATE USER
    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserModel user) {

        try{
            UserModel saved = userService.createUser(user);
            return ResponseEntity.ok(saved);

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 2 GET ALL USERS
    @GetMapping
    public ResponseEntity<?> getUsers(){

        try{
            List<UserModel> users = userService.getAllUsers();
            return ResponseEntity.ok(users);

        }catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // 3 GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){

        try{
            UserModel user = userService.getUserById(id);
            return ResponseEntity.ok(user);

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4 UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserModel user){

        try{
            UserModel updated = userService.updateUser(id, user);
            return ResponseEntity.ok(updated);

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5 DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){

        try{
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");

        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}