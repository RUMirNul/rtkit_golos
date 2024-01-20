package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.UserPollResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPollResultRepo extends JpaRepository<UserPollResult, Integer> {
}
