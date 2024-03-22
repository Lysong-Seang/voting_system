package src;

import java.util.ArrayList;

public class Party {
    private String name;
    private int numVotes;
    private ArrayList<Candidate> candidates;
    private int numAllocatedSeats;

    public Party(String name, int numVotes, ArrayList<Candidate> candidates){
        this.name = name;
        this.candidates = candidates;
        this.numVotes = numVotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }
    
    public void setNumAllocatedSeats(int numSeats) {
        this.numAllocatedSeats = numSeats;
    }

    public int getNumAllocatedSeats() {
        return numAllocatedSeats;
    }
}
