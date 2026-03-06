package net.ikwa.week7project.repo;

import net.ikwa.week7project.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);

}