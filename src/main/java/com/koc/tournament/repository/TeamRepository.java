package com.koc.tournament.repository;

import com.koc.tournament.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByGroupNameOrderByPointsDescSetsWonDescSinglesWonDesc(String groupName);

    @Query("SELECT t FROM Team t WHERE t.groupName = ?1 ORDER BY t.points DESC, t.setsWon DESC, t.singlesWon DESC")
    List<Team> findByGroupNameOrderedByStandings(String groupName);
}
