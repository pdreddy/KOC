package com.koc.tournament.repository;

import com.koc.tournament.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("SELECT m FROM Match m WHERE m.team1.groupName = ?1 OR m.team2.groupName = ?1 ORDER BY m.matchDate DESC")
    List<Match> findByGroupNameOrderByMatchDateDesc(String groupName);
}
