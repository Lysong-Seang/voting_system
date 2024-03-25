package src;

/**
 * This is the Candidate class that stores all candidate information for election
 * @author Fumisato Teranishi
 * @author Shunichi Sawamura
 */
public class Candidate {
    private String name;
    private String party;
    private int numVotes;

     /**
     * Initialize the variables of Canddiate class.
     * @param name
     * @param party
     * @param numVotes
     */
    public Candidate(String name, String party, int numVotes) {
        this.name = name;
        this.party = party;
        this.numVotes = numVotes;
    }

    /**
     * Return the candidate name.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the party name that the candidate belongs.
     * @return party
     */
    public String getParty() {
        return this.party;
    }

    /**
     * Return the number of obtained votes for the candidate.
     * @return numVotes
     */
    public int getNumVotes() {
        return this.numVotes;
    }

    /**
     * Set the number of obtained votes for this candidate.
     * @param voteNum
     */
    public void setNumVotes(int voteNum) {
        this.numVotes = voteNum;
    }
}

