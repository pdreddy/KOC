package com.koc.tournament.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;

    @Column(nullable = false)
    private Integer team1Points;

    @Column(nullable = false)
    private Integer team2Points;

    @Column(nullable = false)
    private Integer team1Sets;

    @Column(nullable = false)
    private Integer team2Sets;

    @Column(nullable = false)
    private Integer team1Singles;

    @Column(nullable = false)
    private Integer team2Singles;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    public Match() {}

    public Match(Team team1, Team team2, Integer team1Points, Integer team2Points,
                 Integer team1Sets, Integer team2Sets, Integer team1Singles, Integer team2Singles) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Points = team1Points;
        this.team2Points = team2Points;
        this.team1Sets = team1Sets;
        this.team2Sets = team2Sets;
        this.team1Singles = team1Singles;
        this.team2Singles = team2Singles;
        this.matchDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Team getTeam1() { return team1; }
    public void setTeam1(Team team1) { this.team1 = team1; }

    public Team getTeam2() { return team2; }
    public void setTeam2(Team team2) { this.team2 = team2; }

    public Integer getTeam1Points() { return team1Points; }
    public void setTeam1Points(Integer team1Points) { this.team1Points = team1Points; }

    public Integer getTeam2Points() { return team2Points; }
    public void setTeam2Points(Integer team2Points) { this.team2Points = team2Points; }

    public Integer getTeam1Sets() { return team1Sets; }
    public void setTeam1Sets(Integer team1Sets) { this.team1Sets = team1Sets; }

    public Integer getTeam2Sets() { return team2Sets; }
    public void setTeam2Sets(Integer team2Sets) { this.team2Sets = team2Sets; }

    public Integer getTeam1Singles() { return team1Singles; }
    public void setTeam1Singles(Integer team1Singles) { this.team1Singles = team1Singles; }

    public Integer getTeam2Singles() { return team2Singles; }
    public void setTeam2Singles(Integer team2Singles) { this.team2Singles = team2Singles; }

    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }
}
