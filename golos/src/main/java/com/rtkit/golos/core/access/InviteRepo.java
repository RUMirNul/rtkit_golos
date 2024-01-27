package com.rtkit.golos.core.access;

import com.rtkit.golos.core.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepo extends JpaRepository<Invite, Integer> {
}
