import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;

/**
 * This is the parent class to both the CPL and OPL class. 
 * It has the methods required to run both types of elections.
 * @author Crystal Wen and Shunichi Sawamura
 */
public class Election{
    protected int totalVotes;
    protected int totalSeats;
    protected ArrayList<Party> parties;
    protected int quota;
    protected ArrayList<Party> winnerList;
    protected BufferedReader br;

    /**
     * Initializes the variables of the Election class.
     * @param totalVotes
     * @param totalSeats
     * @param parties
     * @param br
     */

    public Election(int totalVotes, int totalSeats, ArrayList<Party> parties, BufferedReader br){
        this.totalVotes = totalVotes;
        this.totalSeats = totalSeats;
        this.parties = parties;
        this.br = br;
        winnerList = new ArrayList<>();
    }

    /**
     * Calculates the quota which is the floor of the 
     * total number of votes divided by the total number of seats.
     * @return quota
     */
    public int calculateQuota(){
        int quota = (int) Math.floor(totalVotes/totalSeats);
        return quota;
    }

    /**
     * Counts the votes for each party.
     * This will not work if the total number of votes is less than or equal to 0.
     */
    public void voteCounting() {
        //Reads through each ballot and counts each parties ballots. 
        for (int i = 0; i < totalVotes; i++) {
            String ballot = br.readLine();
            String[] tokens = ballot.split(",");
            int index = tokens.length - 1;
            parties.get(index).setNumVotes(parties.get(index).getNumVotes() + 1);
        }
    }

    /**
     * Simulates a fair coin toss to break a tie between a list of parties.
     * @param winners
     * @return The randomly chosen index of winner 
     */
    public Party coinToss(ArrayList<Party> winners) {
        Random rand = new Random();
        
        // The winning index is randomized 1000 times and the winner
        // is chosen on the 1001th time to simulate a fair coin toss.  
        int index = rand.nextInt(winners.size());
        for(int i = 0; i < 1000; i++) {
            index = rand.nextInt(winners.size());
        }

        return winners.get(index);
    }

    /**
     * Allocate seats to each party.
     */
    public void allocateSeats() {
        int remainingSeats = this.totalSeats;
        //divide the number of votes that each party gets by the quota.
        for (Party party: parties) {
            //seat allocation first round
            int firstAllocatedSeats = party.getNumVotes() / this.quota;
            party.setNumAllocatedSeats(firstAllocatedSeats);
            remainingSeats -= firstAllocatedSeats;
            //update 
        }
        //Distribute remaining seats by comparing the largest remainder of votes 
        //for each party in a round-robin fashion.
        while (remainingSeats > 0){
            int largestRemainingVotes = -1;
            ArrayList<Party> largestVoteParties; allocatedParty;
            for (Party party: parties) {
                //calculate remaining votes
                int remainingVotes = party.getNumVotes() - this.quota * party.getNumAllocatedSeats();
                if (remainingVotes > largestRemainingVotes) {
                    largestRemainingVotes = remainingVotes;
                    largestVoteParties.clear();
                    largestVoteParties.add(party);
                } else if (remainingVotes == largestRemainingVotes) {
                    largestVoteParties.add(party);
                }
            }
            if (largestVoteParties.size() == 1) {
                Party allocatedParty = largestVoteParties.get(0);
                allocatedParty.setNumAllocatedSeats(allocatedParty.getNumAllocatedSeats() + 1);
                remainingSeats -= 1;
            } else if (largestVoteParties.size() > 1) {
                //coin toss to determine seat allocation
                Party allocatedParty = coinToss(largestVoteParties);
                allocatedParty.setNumAllocatedSeats(allocatedParty.getNumAllocatedSeats() + 1);
                remainingSeats -= 1;
            }
        }
    }

    /**
     * Finds the winner based on the party with the most votes.
     */
    public void findWinners() {
        int largestVote = -1;
        
        // Finds the party with most amount of votes.
        for (Party party: parties) {
            int thisPartyVote = party.getNumVotes();
            if (largestVote < thisPartyVote) {
                largestVote = thisPartyVote;
                winnerList.clear();
                winnerList.add(party);
            } else if (largestVote == thisPartyVote) {
                winnerList.add(party);
            }
        }
    }

    /**
     * Calls the DisplayResults class to display the results of the election.
     */
    public void displayResults() {
        DisplayResults results = new DisplayResults(
                "CPL", 
                this.parties.size(), 
                this.totalVotes, 
                this.totalSeats,
                this.winnerList,
                );
    }

    /**
     * Calls the Audit class to create an audit file.
     */
    public void auditFile() {
        Audit auditFile = new Audit(
                "CPL",
                this.parties.size(),
                this.totalVotes,
                this.totalSeats,
                this.winnerList
                );
    }

}


