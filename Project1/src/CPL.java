import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;

public class CPL extends Election{
    public CPL(int totalVotes, int totalSeats, ArrayList<Party> parties, BufferedReader br){
        super(totalVotes, totalSeats, parties, br);
    }
}
