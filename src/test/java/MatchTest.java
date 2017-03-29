import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MatchTest {
  private Match testMatch;

  @Before
  public void setUp() {
    testMatch = new Match("Finney_vs_Fisher_Bantam_Worlds_Gi_Final", "IBJJF Gi Worlds","Long Beach, CA" ,"05-01-2017", "Mark Fisher", "Chris Finney", "Bantam", "Brown",
    "Brown");
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void match_instantiatesCorrectly(){
    assertTrue(testMatch instanceof Match);
  }

}
