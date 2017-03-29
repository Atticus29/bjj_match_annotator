import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MatchTest {
  private Match testMatch;

  @Before
  public void setUp() {
    testMatch = new Match("Finney_vs_Fisher_Bantam_Worlds_Gi_Final", "IBJJF Gi Worlds","Long Beach, CA" ,"05-01-2017", "Mark Fisher", "Chris Finney", "Bantam", "Brown",
    "Brown");
    testMatch.save();
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

  @Test
  public void all_returnsListOfMatches_List(){
      Match testMatch2 = new Match("Mendes_vs_Miyao_Light_Worlds_Gi_Final", "IBJJF Gi Worlds","Long Beach, CA" ,"05-01-2017", "Rafael Mendes", "Paulo Miyao", "Light", "Black",
      "Black");
      testMatch2.save();
      System.out.println("Size is " + Match.all().size());
      // assertTrue(2,Match.all().size());
      assertEquals(true, Match.all().get(0).equals(testMatch));
      assertEquals(true, Match.all().get(1).equals(testMatch2));
  }


  // @Test
  // publid void save_allowsUsToRetrieveASavedMatch_true(){
  //   Match retrievedMatch = Match.all().get(0);
  //   assertTrue(testMatch.equals(retrievedMatch));
  // }

  @Test
  public void equals_returnsWhetherTwoMatchesAreIdentical_true(){
    Match testMatch2 = new Match("Mendes_vs_Miyao_Light_Worlds_Gi_Final", "IBJJF Gi Worlds","Long Beach, CA" ,"05-01-2017", "Rafael Mendes", "Paulo Miyao", "Light", "Black",
    "Black");
    testMatch2.setId(1);
    Match testMatch3 = new Match("Mendes_vs_Miyao_Light_Worlds_Gi_Final", "IBJJF Gi Worlds","Long Beach, CA" ,"05-01-2017", "Rafael Mendes", "Paulo Miyao", "Light", "Black",
    "Black");
    testMatch3.setId(1);
    assertTrue(testMatch2.equals(testMatch3));
  }


}
