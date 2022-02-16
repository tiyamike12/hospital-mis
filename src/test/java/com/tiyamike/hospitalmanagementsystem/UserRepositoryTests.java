package com.tiyamike.hospitalmanagementsystem;

import com.tiyamike.hospitalmanagementsystem.models.AppUser;
import com.tiyamike.hospitalmanagementsystem.repositories.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AppUserRepository userRepository;

    @Test
    public void testCreateUser(){
        AppUser appUser = new AppUser();
        appUser.setEmail("tiya@mail");
        appUser.setFirstname("Tiyamike");
        appUser.setLastname("Nkhono");
        appUser.setPassword("password");

        AppUser savedUser = userRepository.save(appUser);
        AppUser existUser = testEntityManager.find(AppUser.class, savedUser.getId());

        assert appUser.getEmail().equals(existUser.getEmail());
    }
}
