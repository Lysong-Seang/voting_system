package src;

import java.util.ArrayList;
import java.io.*;

/**
 * The CPL class inherits the Election class to run a CPL type election.
 * @author Crystal Wen
 */
public class CPL extends Election{
    public CPL(int totalVotes, int totalSeats, ArrayList<Party> parties, BufferedReader br){
        super(totalVotes, totalSeats, parties, br);
    }


    /**
     * Counts the votes for each party.
     * This will not work if the total number of votes is less than or equal to 0.
     */
    @Override
    public void voteCounting() {
        // Calls IOException if an I/O error occurs while reading the ballot file
        try {
        //Reads through each ballot and counts each parties ballots.
        for (int i = 0; i < totalVotes; i++) {
            String ballot = br.readLine();
            String[] tokens = ballot.split(",");
            int index = tokens.length - 1;
            parties.get(index).setNumVotes(parties.get(index).getNumVotes() + 1);
        }
        } catch (IOException e) {
            System.out.println("Fail to read the ballot file.");
        }
    }


    /**
     * Finds the winner based on the party with the most seats.
     */
    @Override
    public void findWinners() {
        //Each party clarifies the number of allocated seats based on the voting results and candidate information.
        for (Party party: parties) {
            int thisPartySeats = party.getNumAllocatedSeats();
            ArrayList<Candidate> thisPartyCandidates = party.getCandidates();
            int maxAllocation = Math.min(thisPartySeats, thisPartyCandidates.size());

            //Give the seat to candidate from the
            for (int i=0; i<maxAllocation; i++) {
                this.winnerList.add(thisPartyCandidates.get(i));
            }
        }
    }


}

