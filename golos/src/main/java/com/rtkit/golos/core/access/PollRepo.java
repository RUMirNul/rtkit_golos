package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepo extends JpaRepository<Poll, Integer> {
    @Query(value = "select * from poll where authorid = :id", nativeQuery = true)
    List<Poll> findByUserId(@Param("id")Integer id);
}
