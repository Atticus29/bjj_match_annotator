
import org.sql2o.*;

public class Move {
  private int id;
  private int matchId;
  private String name;
  private String description;
  private String difficulty;


  public Move(int matchId, String name, String description, String difficulty) {
    this.matchId = matchId;
    this.name = name;
    this.description = description;
    this.difficulty = difficulty;
  }

  public int getMatchId() {
    return matchId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public static List<Move> all() {
    String sql = "SELECT * FROM moves;";
    try(Connection con = DB.sql2o.open())
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO moves (matchId, name, description, difficulty) VALUES (:matchId, :name, :description, :difficulty)";
        con.createQuery(sql)
        .addParameter("matchId", this.matchId)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("difficulty", this.difficulty)
        .executeUpdate();
    }
  }

  @Override
  public boolean equals(Object otherMove) {
    if (!(otherMove instanceof Move)) {
      return false;
    } else {
      Move newMove = (Move) otherMove;
      return this.getName().equals(newMove.getName()) &&
             this.getDescription().equals(newMove.getDescription()) &&
             this.getDifficulty().equals(newMove.getDifficulty()) &&
             this.getMatchId() == newMove.getMatchId();
    }
  }
}
