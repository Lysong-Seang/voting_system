import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Election{
    protected int totalVotes;
    protected int totalSeats;
    protected ArrayList<Party> parties;
    protected ArrayList<Candidate> winners;
    protected int quota;
    protected Scanner fileReader;

    protected Election(int totalVotes, int totalSeats, ArrayList<Party> parties, Scanner fileReader){
        this.totalVotes = totalVotes;
        this.totalSeats = totalSeats;
        this.parties = parties;
        this.fileReader = fileReader;
    }

    protected int calculateQuota(){
        return (int) Math.floor(totalVotes/totalSeats);
    }

    protected void voteCounting() {

    }

    protected ArrayList<Party> coinToss() {
        
    }

    protected void allocateSeats() {

    }

    protected void findWinners() {

    }

    protected void displayResults() {

    }

    protected void auditFile() {
        
    }
}