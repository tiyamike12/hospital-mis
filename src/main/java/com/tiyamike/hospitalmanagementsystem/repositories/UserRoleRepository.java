package com.tiyamike.hospitalmanagementsystem.repositories;

import com.tiyamike.hospitalmanagementsystem.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "SELECT user_roles.*, users.id, users.firstname, users.lastname, users.username " +
            "FROM user_roles JOIN users ON users.id = user_roles.userId", nativeQuery = true)
    List<UserRole> findAllByRole();
}
