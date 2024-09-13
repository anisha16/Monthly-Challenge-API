package com.SpringProject.ChallengeApp;

import com.SpringProject.ChallengeApp.Challenge;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Challenges")
@CrossOrigin(origins="http://localhost:3000")
public class ChallengeController {
    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> GetAllChallenges() {
        return new ResponseEntity(ChallengeService.GetAllChallenges(), HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<String> AddChallenege(@RequestBody Challenge challenge) {
        boolean IschallengeAdded = challengeService.AddChallenege(challenge);
        if (IschallengeAdded)
            return new ResponseEntity("Challenge Added",HttpStatus.OK);
         else
            return new ResponseEntity("Challenge Not Added",HttpStatus.NOT_FOUND);

    }
    @GetMapping("/Challenges/{month}")
    public ResponseEntity<Challenge> GetChallenge(@PathVariable String month) {

        Challenge challenge= challengeService.getChallenge(month);
        if(challenge!=null)
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable long id, @RequestBody Challenge updatedChallenge) {

        boolean isChallengeUpdated = ChallengeService.updateChallenge(id,updatedChallenge);

        if (isChallengeUpdated)
            return new ResponseEntity("Challenge Updated",HttpStatus.OK);
        else
            return new ResponseEntity("Challenge Not Updated",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteChallenge(@PathVariable long id) {
        boolean isChallengeDelete = challengeService.DeleteChallenge(id);

        if(isChallengeDelete)
            return new ResponseEntity("Challenge Deleted",HttpStatus.OK);
        else
            return new ResponseEntity("Challenge Not Deleted",HttpStatus.NOT_FOUND);

    }

}
