import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;

public class OPL extends Election{
    public OPL(int totalVotes, int totalSeats, ArrayList<Party> parties, BufferedReader br){
        super(totalVotes, totalSeats, parties, br);
    }

    @Override
    public void voteCounting() {
    }

    @Override
    public Candidate coinToss(ArrayList<Candidate> winners) {
    }

    @Override
    public ArrayList<String> findWinners() {
        return null;
    }

    @Override
    public void auditFile() {

    }
}
