package src2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;

public class TestOPL {
    @Test
    public void testVoteCountingOPL() throws IOException{
        ArrayList<Party> expected = new ArrayList<>();
        ArrayList<Party> actual = new ArrayList<>();
        ArrayList<Candidate> candidatesActual = new ArrayList<>();
        ArrayList<Candidate> candidatesExpected = new ArrayList<>();

        ArrayList<Candidate> demCandidates = new ArrayList<>();
        ArrayList<Candidate> repCandidates = new ArrayList<>();
        ArrayList<Candidate> indepCandidates = new ArrayList<>();

        candidatesExpected.add(new Candidate("Pike", "Democratic", 2));
        candidatesExpected.add(new Candidate("Lucy", "Democratic", 1));
        candidatesExpected.add(new Candidate("Beiye", "Democratic", 0));
        candidatesExpected.add(new Candidate("Etta", "Republican", 2));
        candidatesExpected.add(new Candidate("Alawa", "Republican", 2));
        candidatesExpected.add(new Candidate("Sasha", "Independent1", 2));

        candidatesActual.add(new Candidate("Pike", "Democratic", 0));
        candidatesActual.add(new Candidate("Lucy", "Democratic", 0));
        candidatesActual.add(new Candidate("Beiye", "Democratic", 0));
        candidatesActual.add(new Candidate("Etta", "Republican", 0));
        candidatesActual.add(new Candidate("Alawa", "Republican", 0));
        candidatesActual.add(new Candidate("Sasha", "Independent1", 0));

        demCandidates.add(new Candidate("Pike", "Democratic", 0));
        demCandidates.add(new Candidate("Lucy", "Democratic", 0));
        demCandidates.add(new Candidate("Beiye", "Democratic", 0));

        repCandidates.add(new Candidate("Etta", "Republican", 0));
        repCandidates.add(new Candidate("Alawa", "Republican", 0));
        
        indepCandidates.add(new Candidate("Sasha", "Independent1", 0));

        expected.add(new Party("Democratic", 3, demCandidates));
        expected.add(new Party("Republican", 4, repCandidates));
        expected.add(new Party("Independent1", 2, indepCandidates));

        actual.add(new Party("Democratic", 0, demCandidates));
        actual.add(new Party("Republican", 0, repCandidates));
        actual.add(new Party("Independent1", 0, indepCandidates));
        
        int totalVotes = 9;
        int totalSeats = 2;

        FileReader fileReader = new FileReader("Project1/testing/testOPLVote.csv");
        BufferedReader br = new BufferedReader(fileReader);

        OPL opl = new OPL(totalVotes, totalSeats, actual, br, candidatesActual);
        opl.voteCounting();

        for(int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getNumVotes(), opl.parties.get(i).getNumVotes());
        }

        for(int i = 0; i < candidatesExpected.size(); i++) {
            assertEquals(candidatesExpected.get(i).getNumVotes(), opl.candidates.get(i).getNumVotes());
        }
    }

    // @Test 
    // public void testFindWinners(){

    // }
}
