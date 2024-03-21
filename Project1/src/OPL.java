import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;

/**
 * This is the OPL class that inherits from the Election class and runs an OPL type election.
 * @author Crystal Wen and Shunichi Sawamura
*/
public class OPL extends Election{
    protected ArrayList<Candidate> winnerList;
    protected ArrayList<Candidate> candidates; 

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
        
        // The winning index is randomized 1000 times and the winner
        // is chosen on the 1001th time to simulate a fair coin toss.  
        int index = rand.nextInt(winners.size());
        for(int i = 0; i < 1000; i++) {
            index = rand.nextInt(winners.size());
        }

        return winners.get(index);
    }

    @Override
    public void findWinners() {
        for (Party party: parties) {
            int thisPartySeats = party.getNumAllcoatedSeats();
            ArrayList<Candidate> thisPartyCandidates = party.getCandidates();
            int maxAllocation = Math.max(thisPartySeats, thisPartyCandidates.size());
            for (int i=0; i<maxAllocation; i++) {
                int largestVote = -1;
                ArrayList<Candidate> largestVoteCandidates;
                for (Candidate candidate: thisPartyCandidates) {
                    int thisCandidateVote = candidate.getNumVotes();
                    if (largestVote < thisCandidateVote && !this.winnerList.contains(candidate)) {
                        largestVoteCandidate.clear();
                        largestVoteCandidates.add(candidate);
                        largestVote = thisCandidateVote;
                    } else if (largestVote == thisCandidateVote && !this.winnerList.contains(candidate)) {
                        largestVoteCandidates.add(candidate);
                    }
                }
                if (largestVoteCandidates.size() == 1) {
                    this.winnerList.add(largestVoteCandidates.get(0));
                } else if (largestVoteCandidates.size() > 1) {
                    this.winnerList.add(coinTossOPL(largestVoteCandidates));
                }
            }
        }
    }

    @Override
    public void auditFile() {

    }
}
