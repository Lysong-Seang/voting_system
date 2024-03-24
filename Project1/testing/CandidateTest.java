package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandidateTest {
	
	@Test
	public void testCandidateInitialization() {
	   Candidate candidate = new Candidate("John Doe", "Independent", 1000);
	   assertEquals("John Doe", candidate.getName(), "Candidate name should be initialized correctly.");
	   assertEquals("Independent", candidate.getParty(), "Candidate party should be initialized correctly.");
	   assertEquals(1000, candidate.getNumVotes(), "Candidate votes should be initialized correctly.");
	}

	@Test
	public void testSetName() {
		String oldCandidateName = "John Backman";
		String candidateName = "John Doe";
		Candidate candidate = new Candidate(oldCandidateName, "Independent", 1000);
		candidate.setName(candidateName);
	    assertEquals(candidateName, candidate.getName(), "setName should update the candidate's name.");
	}

	@Test
	public void testSetNumVotes() {
		Candidate candidate = new Candidate("John Doe", "Independent", 0);
		int numVotes = 1500;
		candidate.setNumVotes(numVotes);
		assertEquals(numVotes, candidate.getNumVotes(), "setNumVotes should update he candidate's vote count.");
	}

}
