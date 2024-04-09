package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This file tests CoinToss method in CPL and OPL class.
 * @author Crystal Wen
 */
public class TestCoinToss {
    public static void testCoinToss() throws FileNotFoundException{
        ArrayList<Party> parties = new ArrayList<>();
        int totalVotes = 10000;
        int totalSeats = 3;
        FileReader fileReader = new FileReader("testing/testCPLVote.csv");
        BufferedReader br = new BufferedReader(fileReader);
        int c1 = 0, c2 = 0;

        Election election = new Election(totalVotes, totalSeats, parties, br);

        for(int i = 0; i < 1000; i++) {
            int index = election.coinToss(2);

            if(index == 0) c1++;
            else c2++;
        }
        System.out.println("Round 1");
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2 + "\n");

        c1 = 0;
        c2 = 0;
        int c3 = 0;
        
        for(int i = 0; i < 1000; i++) {
            int index = election.coinToss(3);

            if(index == 0) c1++;
            else if (index == 1) c2++;
            else c3++;
        }
        System.out.println("Round 2");
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c3: " + c3);
    }


    public static void main (String[] args) throws FileNotFoundException{
        testCoinToss();
    }    
}
