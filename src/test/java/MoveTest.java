import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MoveTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Move_instantiatesCorrectly_true() {
    Move myMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    assertEquals(true, myMove instanceof Move);
  }

  @Test
  public void Move_instantiatesWithName_String() {
    Move myMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    assertEquals("guillotine choke", myMove.getName());
  }

  @Test
  public void Move_instantiatesWithDescription_String() {
    Move myMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    assertEquals("forward-facing choke hold", myMove.getDescription());
  }

  @Test
  public void Move_instantatiesWithDifficulty_String() {
    Move myMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    assertEquals("beginner", myMove.getDifficulty());
  }

  @Test
  public void Move_instantiatesWithMatchId() {
    Move myMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    assertEquals(1, myMove.getMatchId());
  }

  @Test
  public void equals_returnTrueIfPropertiesAreTheSame() {
    Move firstMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    Move secondMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    assertTrue(firstMove.equals(secondMove));
  }

  @Test
  public void all_returnsAllInstancesOfMove_true() {
    Move firstMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
    firstMove.save();
    Move secondMove = new Move(1, "armbar", "forced hyperextension of the shoulder and elbow joints", "intermediate");
    secondMove.save();
    assertEquals(true, Move.all().get(0).equals(firstMove));
    assertEquals(true, Move.all().get(1).equals(secondMove));
  }

  // @Test
  // public void save_() {
  //   Move myMove = new Move(1, "guillotine choke", "forward-facing choke hold", "beginner");
  //   myMove.save();
  //
  // }
}

// methods to create:
//
// static all()
// static find()
// save()
// update()
// delete()
