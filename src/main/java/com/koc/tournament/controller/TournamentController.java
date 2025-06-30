package com.koc.tournament.controller;

import com.koc.tournament.entity.Match;
import com.koc.tournament.entity.Team;
import com.koc.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/")
    public String index(Model model) {
        List<Team> groupATeams = tournamentService.getTeamStandingsByGroup("A");
        List<Team> groupBTeams = tournamentService.getTeamStandingsByGroup("B");
        List<Match> groupAMatches = tournamentService.getMatchHistoryByGroup("A");
        List<Match> groupBMatches = tournamentService.getMatchHistoryByGroup("B");
        List<Team> allTeams = tournamentService.getAllTeams();

        model.addAttribute("groupATeams", groupATeams);
        model.addAttribute("groupBTeams", groupBTeams);
        model.addAttribute("groupAMatches", groupAMatches);
        model.addAttribute("groupBMatches", groupBMatches);
        model.addAttribute("allTeams", allTeams);

        return "standings";
    }

    @PostMapping("/api/matches")
    @ResponseBody
    public Match addMatch(@RequestParam Long team1Id,
                         @RequestParam Long team2Id,
                         @RequestParam Integer team1Points,
                         @RequestParam Integer team2Points,
                         @RequestParam Integer team1Sets,
                         @RequestParam Integer team2Sets,
                         @RequestParam Integer team1Singles,
                         @RequestParam Integer team2Singles) {
        return tournamentService.addMatchResult(team1Id, team2Id, team1Points, team2Points,
                                               team1Sets, team2Sets, team1Singles, team2Singles);
    }

    @GetMapping("/api/standings/{group}")
    @ResponseBody
    public List<Team> getStandings(@PathVariable String group) {
        return tournamentService.getTeamStandingsByGroup(group);
    }

    @GetMapping("/api/matches/{group}")
    @ResponseBody
    public List<Match> getMatches(@PathVariable String group) {
        return tournamentService.getMatchHistoryByGroup(group);
    }
}
