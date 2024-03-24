package src2;

/**
 * @author Fumisato Teranishi
 */
public class Candidate {
    /**
     * Field descriptions of the Cadidate class
     * @param name
     * @param party
     * @param numVotes
     */
    private String name;
    private String party;
    private int numVotes;

    /**
     * The constructor for Candidate
     */
    public Candidate(String name, String party, int numVotes) {
        this.name = name;
        this.party = party;
        this.numVotes = numVotes;
    }

    /**
     * The get and set methods below
     */

// Obtaining the Name
    public String getName() {
        return this.name;
    }

// Obtaining the Party
    public String getParty() {
        return this.party;
    }

// Obtaining the number of votes
    public int getNumVotes() {
        return this.numVotes;
    }

// Setting the number of votes
    public void setNumVotes(int voteNum) {
        this.numVotes = voteNum;
    }
}
