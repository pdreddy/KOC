package com.koc.tournament.config;

import com.koc.tournament.entity.Team;
import com.koc.tournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only load data if no teams exist
        if (teamRepository.count() == 0) {
            loadTeamData();
        }
    }

    private void loadTeamData() {
        // Group A Teams
        teamRepository.save(new Team("Rally Squad (RS)", "A"));
        teamRepository.save(new Team("Spin Kings (SK)", "A"));
        teamRepository.save(new Team("Topspin Titans (TT)", "A"));
        teamRepository.save(new Team("Karnas Crusaders (KC)", "A"));
        teamRepository.save(new Team("Net Ninjas (NN)", "A"));

        // Group B Teams
        teamRepository.save(new Team("Court Conquerors (CC)", "B"));
        teamRepository.save(new Team("Mega Lions (ML)", "B"));
        teamRepository.save(new Team("Agni Aces (AA)", "B"));
        teamRepository.save(new Team("Baseline Game Masters (BGM)", "B"));
        teamRepository.save(new Team("Rally Royals (RR)", "B"));

        System.out.println("✅ Tournament teams loaded successfully!");
    }
}
