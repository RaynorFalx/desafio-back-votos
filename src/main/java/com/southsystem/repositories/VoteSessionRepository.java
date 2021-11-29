package com.southsystem.repositories;

import com.southsystem.domain.VoteSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {
}
