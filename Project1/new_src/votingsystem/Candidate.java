package votingsystem;


public class Candidate {
    private String name;
    private int numVotes;
    private String partyName;

    public Candidate(String name, String partyName, int numVotes){
        this.name = name;
        this.partyName = partyName;
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

    public String getParty() {
        return partyName;
    }

    /*
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }*/
    

}
