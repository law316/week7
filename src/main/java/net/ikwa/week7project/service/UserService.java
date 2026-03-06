package net.ikwa.week7project.service;

import net.ikwa.week7project.model.UserModel;
import net.ikwa.week7project.repo.UserRepo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // CREATE
    public UserModel createUser(UserModel user) {

        if(user.getName() == null || user.getName().isBlank()){
            throw new RuntimeException("Name is required");
        }

        if(user.getEmail() == null || user.getEmail().isBlank()){
            throw new RuntimeException("Email is required");
        }

        if(user.getRole() == null || user.getRole().isBlank()){
            throw new RuntimeException("Role is required");
        }

        return userRepo.save(user);
    }

    // GET ALL
    public List<UserModel> getAllUsers() {
        return userRepo.findAll();
    }

    // GET ONE
    public UserModel getUserById(Long id) {

        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE
    public UserModel updateUser(Long id, UserModel user) {

        UserModel existing = getUserById(id);

        if(user.getName() != null){
            existing.setName(user.getName());
        }

        if(user.getEmail() != null){
            existing.setEmail(user.getEmail());
        }

        if(user.getRole() != null){
            existing.setRole(user.getRole());
        }

        return userRepo.save(existing);
    }

    // DELETE
    public void deleteUser(Long id) {

        UserModel user = getUserById(id);

        userRepo.delete(user);
    }

}