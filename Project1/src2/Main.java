package src2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
    public static void runElection(String electionType, int totalVotes, int totalSeats, 
                            ArrayList<Party> parties, BufferedReader reader, ArrayList<Candidate> candidates) throws IOException {
        if (electionType.equals("CPL")) {
            CPL cpl = new CPL(totalVotes, totalSeats, parties, reader);
            cpl.calculateQuota();
            cpl.voteCounting();
            cpl.allocateSeats();
            cpl.findWinners();
            cpl.auditFile();
            cpl.displayResults();
        }else {
            OPL opl = new OPL(totalVotes, totalSeats, parties, reader, candidates);
            opl.calculateQuota();
            opl.voteCounting();
            opl.allocateSeats();
            opl.findWinners();
            opl.auditFile();
            opl.displayResults();
        }
    }

    public static void readBallotFile(String file) throws IOException {
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        ArrayList<Party> parties = new ArrayList<Party>();
        ArrayList<String> partyNames = new ArrayList<String>();
        try{

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String electionType = reader.readLine();
            int totalSeats = Integer.parseInt(reader.readLine());
            int totalVotes= Integer.parseInt(reader.readLine());
            
            int numCandidateOrParties = Integer.parseInt(reader.readLine());

            // read the candidate information which party they belong to
            // I am not sure should I use the parties arraylist or not
            if (electionType.equals("OPL")){
                for (int i=0; i< numCandidateOrParties; i++ ){
                    String [] split = reader.readLine().split(",");
                    String partyn = split[0];
                    String name = split[1];
                    Candidate candidate = new Candidate(name, partyn, 0);
                    candidates.add(candidate);
                    //save party name if it has not stored yet
                    if (!partyNames.contains(partyn)) {
                        partyNames.add(partyn);
                    }
                }
                
                for (String partyname: partyNames) {
                    ArrayList<Candidate> thisPartyCandidates = new ArrayList<Candidate>();
                    for (Candidate can: candidates) {
                        if (partyname.equals(can.getParty())) {
                            thisPartyCandidates.add(can);
                        }
                    }
                    Party party = new Party(partyname, 0, thisPartyCandidates);
                    parties.add(party);
                }

            } else if (electionType.equals("CPL")) {
                // after the loop the candidate arraylist will be clear out
                // all information will in parties arraylist

                for (int i=0; i<numCandidateOrParties; i++){
                    ArrayList<Candidate> thisPartyCandidates = new ArrayList<Candidate>();
                    String [] split = reader.readLine().split(",");
                    String partyn = split[0];
                    for (int j=1; j< split.length; j++){
                        String name = split[j];
                        Candidate candidate = new Candidate(name, partyn, 0);
                        thisPartyCandidates.add(candidate);
                    }
                    Party party = new Party(partyn, 0, thisPartyCandidates);
                    parties.add(party);
                }
            }
            runElection(electionType, totalVotes, totalSeats, parties, reader, candidates);
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public static void main(String[] args) throws IOException {
        
        String filename;
        // file name given from command line argument
        if (args.length > 0) {
            filename = args[0];
        // ask file name by text prompt
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your file name: ");
            filename = scanner.nextLine();
        }
        
        File file = new File(filename);
        while (!file.exists()) {
        	System.out.println("File Not Found");
        	Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your file name: ");
            filename = scanner.nextLine();
            file = new File(filename);
        }

        try {
            readBallotFile(filename);
        } catch (NullPointerException e) {
            System.out.println();
        }
        System.out.println();
        System.out.println("End the Process");
    }

}