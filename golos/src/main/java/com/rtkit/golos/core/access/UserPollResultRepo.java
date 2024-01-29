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
    List<UserPollResult> findByUserId(@Param("id") Integer id);

    @Query(value = "select count(*) from userpollresult where pollId = :pollId", nativeQuery = true)
    Integer countAllByPollIdId(@Param("pollId") Integer pollId);

    @Query(value = "select count(*) from userpollresult where pollId = :pollId and status = :status", nativeQuery = true)
    Integer countAllByPollIdAndStatus(@Param("pollId") Integer pollId, @Param("status") String status);
}

