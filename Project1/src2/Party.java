package src2;

import java.util.ArrayList;

/**
 * This is the Party class that defines setters and getters inside like the Candidate class
 * @author Fumisato Teranishi
 */

public class Party {
    private String name;
    private int numVotes;
    private ArrayList<Candidate> candidates;
    private int numAllocatedSeats;

    /**
     * Initializes the variables of the Party class.
     * @param name
     * @param candidate
     * @param numVotes
     * @param numAllocatedSeats
     */

    public Party(String name, int numVotes, ArrayList<Candidate> candidates){
        this.name = name;
        this.candidates = candidates;
        this.numVotes = numVotes;
        this.numAllocatedSeats = 0;
    }

    /**
     * Getting the name of candidates in each election
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getting the number of votes in each election
     * @param numVotes
     */
    public int getNumVotes() {
        return numVotes;
    }

    /**
     * Setting the number of votes in each election
     * @param numVotes
     */

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    /**
     * Getting the candidate inside the arraylist in each election
     * @param candidates
     */

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    /**
     * Setting the candidate inside the arraylist in each election
     * @param candidates
     */

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    /**
     * Setting the number of allocated seats in each election
     * @return numAllocatedSeats
     */
    public void setNumAllocatedSeats(int numSeats) {
        this.numAllocatedSeats = numSeats;
    }

    /**
     * Getting the number of allocated seats in each election
     * @return numAllocatedSeats
     */
    public int getNumAllocatedSeats() {
        return numAllocatedSeats;
    }
}