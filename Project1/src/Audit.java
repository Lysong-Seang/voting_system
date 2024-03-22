package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * class is responsible for managing and displaying the results of an election.
 * It includes details such as the type of election, the number of parties, ballots, seats, and the winners list.
 * This class also calculates the quota based on the number of ballots and seats, and provides functionality
 * to display detailed election results including the percentage of votes and the allocation of seats among candidates.
 * @author Lysong Seang
 */

public class Audit {
    private String electionType;
    private int numParties, numBallots, numSeats, quota;
    private ArrayList<Candidate> winnerList;
    private ArrayList<Party> parties;

    /**
     * object with the specified details of the election.
     * @param electionType the type of the election.
     * @param numParties the number of parties participating in the election.
     * @param numBallots the total number of ballots cast.
     * @param numSeats the total number of seats available.
     * @param winnerList a list of candidates who have won in the election.
     */
    public Audit(String electionType, int numParties, int numBallots, int numSeats,
                 ArrayList<Candidate> winnerList, ArrayList<Party> parties){
        this.electionType = electionType;
        this.numParties = numParties;
        this.numBallots = numBallots;
        this.numSeats = numSeats;
        this.winnerList = winnerList;
        this.parties = parties;
        this.quota = numBallots / numSeats;
    }

    /**
     * Write the results of the election. This method prints detailed information about the election,
     * including the type of election, number of parties, candidates, seats, ballots, and quotas.
     * It also displays the winners and the number of seats allocated to each party, as well as the percentage of votes
     * received by each party and candidate.
     */

    public void audit() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("auditFile.txt"));
        writer.write("Election type: \n" + electionType);
        writer.write("Number of Parties: " + parties.size());
        int count=0;
        for (int i=0 ; i<parties.size(); i++){
            int numCandidate = parties.get(i).getCandidates().size();
        writer.write("----Election Results----\n");
            count += numCandidate;
        }

        writer.write("Number of Seats: " + numSeats);
        writer.write("Number of Ballots: \n" + numBallots);
        writer.write("Number of Candidates: " + count);
        writer.write("Number of Quotas : "+ this.quota);
        if (electionType.equals("CPL")) {
            for (int i = 0; i < parties.size(); i++) {
                int numberVote = parties.get(i).getNumAllocatedSeats();
                writer.write(parties.get(i).getName() + ": Number of Seats: " + numberVote);
                writer.write("*** Winner(s): ");
                if (winnerList.size() <=0 ){
                    writer.write("N/A ***");
                }
                else{
                    for (int j = 0; j < winnerList.size()-1; j++) {
                        writer.write(winnerList.get(j).getName()+", ");
                    }
                    writer.write(winnerList.get(winnerList.size()-1).getName());
                }
                writer.write(" ***\n");
                writer.write("Number of Votes: " + parties.get(i).getNumVotes());
                writer.write("% of votes: " + (parties.get(i).getNumVotes() / (double)numBallots) * 100);

                ArrayList<Candidate> arrayName = parties.get(i).getCandidates();
                writer.write("Candidate(s): ");

                for (int k =0; k< arrayName.size(); k++){
                    writer.write(arrayName.get(i).getName()+", ");
                }
                writer.write("_____________________________________________\n");
            }
            //System.out.println("% of seats: "+  arrayName/numSeats);

        }
        else {
            if (electionType.equals("OPL")){
                writer.write("***** Winner *****\n");
                for (int i=0; i< winnerList.size(); i++){
                    writer.write((i+1)+". "+winnerList.get(i).getName()+" ("+
                            (winnerList.get(i).getNumVotes()/(double)numBallots)*100+ " / "+winnerList.get(i).getNumVotes()+")");

                }
               writer.write("\n");
                writer.write("***** Candidate *****\n");
                for(int j=0; j< parties.size(); j++){
                    ArrayList<Candidate> arrayName = parties.get(j).getCandidates();
                    writer.write(parties.get(j).getName()+" Won: "+parties.get(j).getNumAllocatedSeats()+ "seat(s)\n");
                    writer.write("Candidate(s): ");
                    for (int k=0; k< arrayName.size()-1; k++){
                        writer.write(arrayName.get(k).getName()+", ");
                    }
                    writer.write(arrayName.get(arrayName.size()-1).getName()+"\n");
                }


            }
        }
    }
}