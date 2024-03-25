package src;

import java.util.ArrayList;

/**
 * This is the Party class that stores all related information for election.
 * @author Fumisato Teranishi
 * @author Shunichi Sawamura
 */
public class Party {
    private String name;
    private int numVotes;
    private ArrayList<Candidate> candidates;
    private int numAllocatedSeats;

    /**
     * Initializes the variables of the Party class.
     * @param name
     * @param numVotes
     * @param candidates
     */
    public Party(String name, int numVotes, ArrayList<Candidate> candidates){
        this.name = name;
        this.candidates = candidates;
        this.numVotes = numVotes;
        this.numAllocatedSeats = 0;
    }

    /**
     * Getting the name of the party.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getting the number of obtained votes for the party.
     * @return numVotes
     */
    public int getNumVotes() {
        return numVotes;
    }

    /**
     * Setting the number of obtained votes for the party.
     * @param numVotes
     */
    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    /**
     * Getting the candidates list who belong to the party
     * @return candidates
     */
    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    /**
     * Setting the candidates list who belong to the party 
     * @param candidates 
     */
    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    /**
     * Setting the number of allocated seats for the party
     * @param numSeats
     */
    public void setNumAllocatedSeats(int numSeats) {
        this.numAllocatedSeats = numSeats;
    }

    /**
     * Getting the number of allocated seats for the party
     * @return numAllocatedSeats
     */
    public int getNumAllocatedSeats() {
        return numAllocatedSeats;
    }
}
