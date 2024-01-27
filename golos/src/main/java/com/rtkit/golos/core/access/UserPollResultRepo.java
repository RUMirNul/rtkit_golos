package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.UserPollResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPollResultRepo extends JpaRepository<UserPollResult, Integer> {
    @Query(value = "select * from userpollresult where userid = :id", nativeQuery = true)
    List<UserPollResult> findByUserId(@Param("id")Integer id);
}

