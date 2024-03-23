package testing;

import org.junit.Test;

import src.Party;
import src.CPL;
import src.Candidate;
import src.OPL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

    @Test
    public void testCoinTossOPL() throws FileNotFoundException {
        int totalVotes = 10000;
        int totalSeats = 3;
        ArrayList<Party> parties = new ArrayList<>();
        FileReader fileReader = new FileReader("Project1/testing/testCPLVote.csv");
        BufferedReader br = new BufferedReader(fileReader);
        ArrayList<Candidate> candidates = new ArrayList<>();

        ArrayList<Candidate> testWinner = new ArrayList<>();
        testWinner.add(new Candidate("Sara", "Democratic", 10));
        testWinner.add(new Candidate("Bob Mc'Bobson", "Republican", 10));

        OPL opl = new OPL(totalVotes, totalSeats, parties, br, candidates);
        Candidate actualWinner = opl.coinTossOPL(testWinner);

        assertNotNull(actualWinner);

        testWinner.add(new Candidate("Steve Mc'Steveson", "Green", 10));
        testWinner.add(new Candidate("Renee", "Independent", 10));

        actualWinner = opl.coinTossOPL(testWinner);
        assertNotNull(actualWinner);
    }

    // @Test 
    // public void testFindWinners(){

    // }
}
