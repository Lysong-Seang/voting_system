import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;

/**
 * The OPL class that inherits from the Election class and runs an OPL type election.
 * @author Crystal Wen 
 * @author Shunichi Sawamura
*/
public class OPL extends Election{
    private ArrayList<Candidate> winnerList;
    private ArrayList<Candidate> candidates; 

    /**
     * The constructor for OPL. It initalizes all variables when a user calls it.
     * @param totalVotes
     * @param totalSeats
     * @param parties
     * @param br
     * @param candidates
     */
    public OPL(int totalVotes, int totalSeats, ArrayList<Party> parties, BufferedReader br, ArrayList<Candidate> candidates){
        super(totalVotes, totalSeats, parties, br);
        winnerList = new ArrayList<>();
        this.candidates = candidates;
    }

    /**
     * Counts the votes each candidate and their respective party has.
     */
    @Override
    public void voteCounting() {
        //Sums up the number of votes for each candidate.
        for (int i = 0; i < totalVotes; i++) {
            String ballot = br.readLine();
            String[] tokens = ballot.split(",");
            int index = tokens.length - 1;
            candidates.get(index).setNumVotes(candidates.get(index).getNumVotes() + 1);
        }

        /*Finds the party of each respective candidate and sums up the votes of the candiates
        in their respective party to determine the number of votes that their party gets. */ 
        for (Candidate candidate:candidates) {
            int candidateVotes = candidate.getNumVotes();
            int partyIndex = parties.indexOf(candidate.getParty());
            int canIndex = parties.get(partyIndex).getCandidates.indexOf(candidate.getName());

            parties.get(partyIndex).getCandidates.get(canIndex).setNumVotes(candidateVotes);
            parties.get(partyIndex).setNumVotes(parties.get(partyIndex).getNumVotes() + candidateVotes);
        }
    }

    /**
     * Simulates a fair coin toss to break a tie between a list of parties.
     * @param winners
     * @return The randomly chosen index of winner 
     */
     public Candidate coinTossOPL(ArrayList<Candidate> winners) {
        Random rand = new Random();
        
        /* The winning index is randomized 1000 times and the winner
        is chosen on the 1001th time to simulate a fair coin toss. */ 
        int index = rand.nextInt(winners.size());
        for(int i = 0; i < 1000; i++) {
            index = rand.nextInt(winners.size());
        }

        return winners.get(index);
    }

    /**
     * Finds the winner based on the candidate with the most votes.
     */
    @Override
    public void findWinners() {
        for (Party party: parties) {
            int thisPartySeats = party.getNumAllcoatedSeats();
            ArrayList<Candidate> thisPartyCandidates = party.getCandidates();
            int maxAllocation = Math.max(thisPartySeats, thisPartyCandidates.size());

            // Goes through all of the party in the election.
            for (int i=0; i<maxAllocation; i++) {
                int largestVote = -1;
                ArrayList<Candidate> largestVoteCandidates;

                // Finds the candidate with the most vote.
                for (Candidate candidate: thisPartyCandidates) {
                    int thisCandidateVote = candidate.getNumVotes();
                    
                    /* If the current candidate has more votes than the previous candidate, 
                    the candidate will replace the one in the list.
                    Otherwise, if the current candidate has the same amount of votes, 
                    it will be added to the list of winners. */  
                    if (largestVote < thisCandidateVote && !this.winnerList.contains(candidate)) {
                        largestVoteCandidates.clear();
                        largestVoteCandidates.add(candidate);
                        largestVote = thisCandidateVote;
                    } else if (largestVote == thisCandidateVote && !this.winnerList.contains(candidate)) {
                        largestVoteCandidates.add(candidate);
                    }
                }

                // Checks if there are candidates who tied.
                // If there are ties, it should find the winner of that tie breaker
                if (largestVoteCandidates.size() == 1) {
                    this.winnerList.add(largestVoteCandidates.get(0));
                } else if (largestVoteCandidates.size() > 1) {
                    this.winnerList.add(coinTossOPL(largestVoteCandidates));
                }
            }
        }
    }

    /**
     * Calls the Display class to display the results of the election.
     */
    @Override
    public void displayResults() {
        DisplayResults results = new DisplayResults(
                "OPL", 
                this.parties.size(), 
                this.totalVotes, 
                this.totalSeats,
                this.winnerList,
                );
    }

    /**
     * Calls the Audit class to create an audit file.
     */
    @Override
    public void auditFile() {
        Audit auditFile = new Audit(
                "OPL",
                this.parties.size(),
                this.totalVotes,
                this.totalSeats,
                this.winnerList
                );
    }
}
