// import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.random.*;

public class Election{
    protected int totalVotes;
    protected int totalSeats;
    protected ArrayList<Party> parties;
    protected int quota;
    protected Scanner fileReader;

    public Election(int totalVotes, int totalSeats, ArrayList<Party> parties, Scanner fileReader){
        this.totalVotes = totalVotes;
        this.totalSeats = totalSeats;
        this.parties = parties;
        this.fileReader = fileReader;
    }

    public int calculateQuota(){
        return (int) Math.floor(totalVotes/totalSeats);
    }

    public void voteCounting() {

    }

    public ArrayList<Party> coinToss(ArrayList<Party> tiedParties) {
        ArrayList<Party> winners= new ArrayList<>();
        return winners;
    }

    public void allocateSeats() {

    }

    public void findWinners() {

    }

    public void displayResults() {

    }

    public void auditFile() {
        
    }
}