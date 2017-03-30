
import org.sql2o.*;
import java.util.List;

public class Move {
  private int id;
  private int match_id;
  private String name;
  private String description;
  private String difficulty;


  public Move(int matchId, String name, String description, String difficulty) {
    this.match_id = matchId;
    this.name = name;
    this.description = description;
    this.difficulty = difficulty;
  }

  public int getMatchId() {
    return match_id;
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

  public int getId() {
    return id;
  }

  public static List<Move> all() {
    String sql = "SELECT * FROM moves;";
    try(Connection con = DB.sql2o.open()) {
      List<Move> allMoves = con.createQuery(sql).executeAndFetch(Move.class);
      return allMoves;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO moves (match_id, name, description, difficulty) VALUES (:match_id, :name, :description, :difficulty)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("match_id", this.match_id)
          .addParameter("name", this.name)
          .addParameter("description", this.description)
          .addParameter("difficulty", this.difficulty)
          .executeUpdate()
          .getKey();
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

  public static Move find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM moves WHERE id = :id;";
      Move myMove = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Move.class);
      return myMove;
    }
  }

  public void update(int matchId, String name, String description, String difficulty) {
      String sql = "UPDATE moves SET match_id=:match_id, name=:name, description=:description, difficulty=:difficulty WHERE id=:id;";
      try(Connection con = DB.sql2o.open()) {
        con.createQuery(sql)
          .addParameter("match_id", match_id)
          .addParameter("name", name)
          .addParameter("description", description)
          .addParameter("difficulty", difficulty)
          .addParameter("id", this.id)
          .executeUpdate();
        }
  }

  public void delete() {
    String sql = "DELETE FROM moves WHERE id=:id;";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  // Validate difficult input method here

}
