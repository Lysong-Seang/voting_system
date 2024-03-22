package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private String name;
    private BufferedReader br;
    private String electionType;
    private ArrayList<Candidate> candidates;
    private ArrayList <Party> parties;
    private OPL opl;
    private CPL cpl;
    private int seats;
    private int ballots;

    public static void main(String[] args) throws IOException {
        
        String filename;
        // file name given from command line argument
        if (args.length > 0) {
            filename = args[0];
        // ask file name by text prompt
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your file name: ");
            filename = scanner.nextLine();
        }
        
        Main main = new Main();
        try {
            main.readBallotFile(filename);
            main.runElection();
        } catch (NullPointerException e) {
            System.out.println("End the Process");
        }
    }

    public Main(){
        
    }

    public void readBallotFile(String file) throws IOException {
        this.candidates = new ArrayList<Candidate>();
        this.parties = new ArrayList<Party>();
        ArrayList<String> partyNames = new ArrayList<String>();
        try{

            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            this.electionType = reader.readLine();
            int totalSeats = Integer.parseInt(reader.readLine());
            int totalVotes= Integer.parseInt(reader.readLine());
            //this.seats = Integer.parseInt(reader.readLine());
            //this.ballots = Integer.parseInt(reader.readLine());
            int numCandidateOrParties = Integer.parseInt(reader.readLine());

            // read the candidate information which party they belong to
            // I am not sure should I use the parties arraylist or not
            if (this.electionType.equals("OPL")){
                for (int i=0; i< numCandidateOrParties; i++ ){
                    String [] split = reader.readLine().split(",");
                    String partyn = split[0];
                    String name = split[1];
                    Candidate candidate = new Candidate(name, partyn, 0);
                    this.candidates.add(candidate);
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

                // Delete later
                System.out.println("total seat, total vote : " + totalSeats + " " + totalVotes);
                System.out.println("num candidates or parties: " + numCandidateOrParties);
                for (Party party: parties) {
                    System.out.println(party.getName());
                    for (Candidate _can: party.getCandidates()) {
                        System.out.println(_can.getName());
                        System.out.println(_can.getParty());
                    }
                    System.out.println(" ");

                }

                this.opl = new OPL(totalVotes, totalSeats, parties, reader, candidates);

            } else if (this.electionType.equals("CPL")) {
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
                    Party party = new Party(partyn, 0, candidates);
                    parties.add(party);
                }
                this.cpl = new CPL(totalVotes, totalSeats, parties, reader);
            }
        } catch (FileNotFoundException e){
            System.out.println("File does not exist");
        }
    }

    public void runElection() throws IOException {
        if (electionType.equals("CPL")) {
            cpl.calculateQuota();
            cpl.voteCounting();
            cpl.allocateSeats();
            cpl.findWinners();
            cpl.auditFile();
            cpl.displayResults();
        }
        if (this.electionType.equals("OPL")){
            opl.calculateQuota();
            opl.voteCounting();
            opl.allocateSeats();
            opl.findWinners();
            opl.auditFile();
            opl.displayResults();
        }

    }

}