package testing;

import org.junit.Test;

import src.Party;
import src.Candidate;
import src.CPL;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;

public class TestCPL {
    // @Test
    // public void testCalculateQuota() {

    // }

    @Test
    public void testVoteCounting() throws IOException {
        ArrayList<Party> expected = new ArrayList<>();
        ArrayList<Party> actual = new ArrayList<>();

        ArrayList<Candidate> demCandidates = new ArrayList<>();
        ArrayList<Candidate> repCandidates = new ArrayList<>();
        ArrayList<Candidate> waveCandidates = new ArrayList<>();
        ArrayList<Candidate> reformCandidates = new ArrayList<>();
        ArrayList<Candidate> greenCandidates =  new ArrayList<>();
        ArrayList<Candidate> indepCandidates = new ArrayList<>();

        demCandidates.add(new Candidate("Joe", "Democratic", 0));
        demCandidates.add(new Candidate("Sally", "Democratic", 0));
        demCandidates.add(new Candidate("Ahmed", "Democratic", 0));

        repCandidates.add(new Candidate("Allen", "Republican", 0));
        repCandidates.add(new Candidate("Nikki", "Republican", 0));
        repCandidates.add(new Candidate("Taihui", "Republican", 0));

        waveCandidates.add(new Candidate("Sarah", "New Wave", 0));

        reformCandidates.add(new Candidate("Xinyue", "Reform", 0));
        reformCandidates.add(new Candidate("Nikita", "Reform", 0));

        greenCandidates.add(new Candidate("Bethany", "Green", 0));
        
        indepCandidates.add(new Candidate("Mike", "Independent", 0));

        expected.add(new Party("Democratic", 3, demCandidates));
        expected.add(new Party("Republican", 2, repCandidates));
        expected.add(new Party("New Wave", 0, waveCandidates));
        expected.add(new Party("Reform", 2, reformCandidates));
        expected.add(new Party("Green", 1, greenCandidates));
        expected.add(new Party("Independent", 1, indepCandidates));

        actual.add(new Party("Democratic", 0, demCandidates));
        actual.add(new Party("Republican", 0, repCandidates));
        actual.add(new Party("New Wave", 0, waveCandidates));
        actual.add(new Party("Reform", 0, reformCandidates));
        actual.add(new Party("Green", 0, greenCandidates));
        actual.add(new Party("Independent", 0, indepCandidates));

        int totalVotes = 9;
        int totalSeats = 3;

        FileReader fileReader = new FileReader("Project1/testing/testCPLVote.csv");
        BufferedReader br = new BufferedReader(fileReader);

        CPL cpl = new CPL(totalVotes, totalSeats, actual, br);
        cpl.voteCounting();
        
        for(int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).getNumVotes(), cpl.parties.get(i).getNumVotes());
        }
        
    }

    // @Test
    // public void testCoinToss(){

    // }

    // @Test
    // public void testAllocateSeats() {

    // }

    // @Test 
    // public void testFindWinners(){

    // }
}



