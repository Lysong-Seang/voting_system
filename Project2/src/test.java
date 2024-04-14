package src;
import java.util.*;

public class test {
    public static void main(String[] args) {
        ArrayList<Candidate> d = new ArrayList<>();
        ArrayList<Candidate> r = new ArrayList<>();
        ArrayList<Candidate> i = new ArrayList<>();
        ArrayList<Candidate> candidates = new ArrayList<>();
        ArrayList<Party> parties = new ArrayList<>();

        

        Candidate c1 = new Candidate("Pike", "D", 3);
        Candidate c2 = new Candidate("Foster", "D", 3);
        Candidate c3 = new Candidate("Deutsch", "R", 0);
        Candidate c4 = new Candidate("Borg", "I", 4);

        candidates.add(c1);
        candidates.add(c2);
        candidates.add(c3);
        candidates.add(c4);

        d.add(c1);
        d.add(c2);
        r.add(c3);
        i.add(c4);

        parties.add(new Party("D", 6, d));
        parties.add(new Party("R", 0, r));
        parties.add(new Party("I", 4, i));

        MPO mpo = new MPO(9, 3, parties, new ArrayList<String[]>(), candidates);
        mpo.allocateSeats();
        mpo.findWinners();
        mpo.displayResults("MPO");
    }
}
