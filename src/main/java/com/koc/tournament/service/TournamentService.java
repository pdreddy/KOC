package com.koc.tournament.service;

import com.koc.tournament.entity.Match;
import com.koc.tournament.entity.Team;
import com.koc.tournament.repository.MatchRepository;
import com.koc.tournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    public List<Team> getTeamStandingsByGroup(String groupName) {
        return teamRepository.findByGroupNameOrderedByStandings(groupName);
    }

    public List<Match> getMatchHistoryByGroup(String groupName) {
        return matchRepository.findByGroupNameOrderByMatchDateDesc(groupName);
    }

    @Transactional
    public Match addMatchResult(Long team1Id, Long team2Id, Integer team1Points, Integer team2Points,
                               Integer team1Sets, Integer team2Sets, Integer team1Singles, Integer team2Singles) {
        Team team1 = teamRepository.findById(team1Id).orElseThrow();
        Team team2 = teamRepository.findById(team2Id).orElseThrow();

        team1.setPoints(team1.getPoints() + team1Points);
        team1.setSetsWon(team1.getSetsWon() + team1Sets);
        team1.setSinglesWon(team1.getSinglesWon() + team1Singles);
        team1.setMatchesPlayed(team1.getMatchesPlayed() + 1);

        team2.setPoints(team2.getPoints() + team2Points);
        team2.setSetsWon(team2.getSetsWon() + team2Sets);
        team2.setSinglesWon(team2.getSinglesWon() + team2Singles);
        team2.setMatchesPlayed(team2.getMatchesPlayed() + 1);

        teamRepository.save(team1);
        teamRepository.save(team2);

        Match match = new Match(team1, team2, team1Points, team2Points,
                               team1Sets, team2Sets, team1Singles, team2Singles);
        return matchRepository.save(match);
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
