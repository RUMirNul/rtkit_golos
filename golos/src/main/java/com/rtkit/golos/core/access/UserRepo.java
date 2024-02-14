package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.GolosUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<GolosUser, Integer> {
    GolosUser findByEmail(String email);
}
