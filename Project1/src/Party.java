import java.util.ArrayList;

public class Party {
    String name;
    int votes;
    ArrayList<Candidate> candidates;

    public Party(String name, ArrayList<Candidate> candidates){
        this.name = name;
        this.candidates = candidates;
        this.votes = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }
    
}
