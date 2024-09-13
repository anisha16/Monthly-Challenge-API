package com.SpringProject.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {
  //  private static List<Challenge> challenges = new ArrayList<>();
    private long nextid=1L;

@Autowired
static ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;

    }

    public static List<Challenge> GetAllChallenges() {
        return challengeRepository.findAll();
    }

    public Boolean AddChallenege(@RequestBody Challenge challenge) {
        if(challenge!=null)
        {   challenge.setId(nextid++);
            challengeRepository.save(challenge);
            return true;
        }
        else{
            return false;
        }

    }


    public static boolean updateChallenge(long id, Challenge updatedChallenge) {

       Optional <Challenge> challenge = challengeRepository.findById(id);
        if(challenge.isPresent()) {
            Challenge challengeToUpdate = challenge.get();
            challengeToUpdate.setMonth(updatedChallenge.getMonth());
            challengeToUpdate.setDescription(updatedChallenge.getDescription());
            challengeRepository.save(challengeToUpdate);

            return true;
        }
        return false;

    }


    public Challenge getChallenge(String month) {
        Optional<Challenge> challenge = challengeRepository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);


    }

    public boolean DeleteChallenge(long id) {
        Optional <Challenge> challenge = challengeRepository.findById(id);
        if(challenge.isPresent()){

        challengeRepository.deleteById(id);
        return true;

        }
        return false;



    }
}