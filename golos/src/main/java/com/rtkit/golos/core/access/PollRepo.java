package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepo extends JpaRepository<Poll, Integer> {
}
