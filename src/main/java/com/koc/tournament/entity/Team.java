package com.koc.tournament.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false)
    private Integer points = 0;

    @Column(nullable = false)
    private Integer setsWon = 0;

    @Column(nullable = false)
    private Integer singlesWon = 0;

    @Column(nullable = false)
    private Integer matchesPlayed = 0;

    public Team() {}

    public Team(String name, String groupName) {
        this.name = name;
        this.groupName = groupName;
        this.points = 0;
        this.setsWon = 0;
        this.singlesWon = 0;
        this.matchesPlayed = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public Integer getSetsWon() { return setsWon; }
    public void setSetsWon(Integer setsWon) { this.setsWon = setsWon; }

    public Integer getSinglesWon() { return singlesWon; }
    public void setSinglesWon(Integer singlesWon) { this.singlesWon = singlesWon; }

    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer matchesPlayed) { this.matchesPlayed = matchesPlayed; }
}
