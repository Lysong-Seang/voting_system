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
    protected ArrayList<Candidate> winnerList;

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
        int remainingSeats = this.totalSeats;
        //divide the number of votes that each party gets by the quota.
        for (Party party: parties) {
            //seat allocation first round
            int firstAllocatedSeats = party.getNumVotes() / this.quota;
            party.setNumAllocatedSeats(firstAllocateSeats);
            remainingSeats -= firstAllocatedSeats;
            //update 
        }
        //Distribute remaining seats by comparing the largest remainder of votes 
        //for each party in a round-robin fashion.
        while (remainingSeats > 0){
            int largestRemainingVotes = -1;
            Party allocatedParty;
            for (Party party: parties) {
                //calculate remaining votes
                int remainingVotes = party.getNumVotes() - this.quota * party.getNumAllocatedSeats();
                if (remainingVotes > largestRemainingVotes) {
                    largestRemainingVotes = remainingVotes;
                    allocatedParty = party;
                }
            }
            allocatedParty.setNumAllocatedSeats(allocatedParty.getNumAllocatedSeats() + 1);
        }
    }

    public void findWinners() {
        
    }

    public void displayResults() {
        DisplayResults results = new DisplayResults(
                "CPL", 
                this.parties.size(), 
                this.totalVotes, 
                this.totalSeats,
                this.winnerList,
                );
    }

    public void auditFile() {
        Audit auditFile = new Audit(
                "CPL",
                this.parties.size();
                this.totalVotes,
                this.totalSeats,
                this.winnerList
                );
    }

}


