import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MatchTest {
  private Match testMatch;

  @Before
  public void setUp() {
    testMatch = new Match("Finney_vs_Fisher_Bantam_Worlds_Gi_Final", "IBJJF Gi Worlds","Long Beach, CA" ,"05-01-2017", "Mark Fisher", "Chris Finney", "Bantam", "Brown",
    "Brown");
    // testMatch.save();
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void match_instantiatesCorrectly(){
    assertTrue(testMatch instanceof Match);
  }

  @Test
  public void getters_returnExpectedValues_true(){
    assertEquals("Finney_vs_Fisher_Bantam_Worlds_Gi_Final", testMatch.getName());
    assertEquals("IBJJF Gi Worlds", testMatch.getHost_org());
    assertEquals("Long Beach, CA", testMatch.getLocation());
    assertEquals("05-01-2017", testMatch.getDate());
    assertEquals("Mark Fisher", testMatch.getAthlete_1_name());
    assertEquals("Chris Finney", testMatch.getAthlete_2_name());
    assertEquals("Bantam", testMatch.getWeight_class());
    assertEquals("Brown", testMatch.getAthlete_1_belt());
    assertEquals("Brown", testMatch.getAthlete_2_belt());
    testMatch.setId(4);
    assertEquals(4, testMatch.getId());
  }

  // @Test
  // public void equals_returnsWhetherTwoMatchesAreIdentical_true(){
  //
  // }

  // @Test
  // public void all_returnsListOfMatches_List(){
  //
  // }

}
