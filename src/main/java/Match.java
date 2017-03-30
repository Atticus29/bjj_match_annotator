import org.sql2o.*;
import java.util.List;

public class Match {
  private int id;
  private String name;
  private String host_org;
  private String location;
  private String date;
  private String athlete_1_name;
  private String athlete_2_name;
  private String weight_class;
  private String athlete_1_belt;
  private String athlete_2_belt;
  private String matchURL;

  public Match(String name, String host, String location, String date, String a1Name, String a2Name, String weight_class, String a1Belt, String a2Belt, String matchURL) {
    this.name = name;
    this.host_org = host;
    this.location = location;
    this.date = date;
    this.athlete_1_name = a1Name;
    this.athlete_2_name = a2Name;
    this.weight_class = weight_class;
    this.athlete_1_belt = a1Belt;
    this.athlete_2_belt = a2Belt;
    this.matchURL = matchURL;
  }

  @Override
  public boolean equals(Object match2){
    if(! (match2 instanceof Match)){
      return false;
    } else{
      Match newMatch = (Match) match2;
      return this.getName().equals(newMatch.getName()) &&
      this.getHost_org().equals(newMatch.getHost_org()) &&
      this.getLocation().equals(newMatch.getLocation()) &&
      this.getDate().equals(newMatch.getDate()) &&
      this.getAthlete_1_name().equals(newMatch.getAthlete_1_name()) &&
      this.getAthlete_2_name().equals(newMatch.getAthlete_2_name()) &&
      this.getWeight_class().equals(newMatch.getWeight_class()) &&
      this.getAthlete_1_belt().equals(newMatch.getAthlete_1_belt()) &&
      this.getAthlete_2_belt().equals(newMatch.getAthlete_2_belt()) &&
      this.getMatchURL().equals(newMatch.getMatchURL()) &&
      this.getId() == newMatch.getId();
    }
  }

  public void setId(int id){
    this.id = id;
  }

  public int getId(){
    return this.id;
  }

  public String getName(){
    return this.name;
  }

  public String getHost_org(){
    return this.host_org;
  }
  public String getLocation(){
    return this.location;
  }
  public String getDate(){
    return this.date;
  }
  public String getAthlete_1_name(){
    return this.athlete_1_name;
  }
  public String getAthlete_2_name(){
    return this.athlete_2_name;
  }
  public String getWeight_class(){
    return this.weight_class;
  }
  public String getAthlete_1_belt(){
    return this.athlete_1_belt;
  }
  public String getAthlete_2_belt(){
    return this.athlete_2_belt;
  }

  public String getMatchURL(){
    return this.matchURL;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sqlCommand = "INSERT INTO matches (name, host_org, location, date, athlete_1_name, athlete_2_name, weight_class, athlete_1_belt, athlete_2_belt, matchurl) VALUES (:name, :host_org, :location, :date, :athlete_1_name, :athlete_2_name, :weight_class, :athlete_1_belt, :athlete_2_belt, :matchurl);";
      this.id = (int) con.createQuery(sqlCommand, true)
        .addParameter("name", this.name)
        .addParameter("host_org", this.host_org)
        .addParameter("location", this.location)
        .addParameter("date", this.date)
        .addParameter("athlete_1_name", this.athlete_1_name)
        .addParameter("athlete_2_name", this.athlete_2_name)
        .addParameter("weight_class", this.weight_class)
        .addParameter("athlete_1_belt", this.athlete_1_belt)
        .addParameter("athlete_2_belt", this.athlete_2_belt)
        .addParameter("matchurl", this.matchURL)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Match> all(){
    try(Connection con= DB.sql2o.open()){
      String sqlCommand = "SELECT * FROM matches;";
      List<Match> results = con.createQuery(sqlCommand)
        .executeAndFetch(Match.class);
      return results;
    }
  }

  public static Match find(int id){
    try(Connection con = DB.sql2o.open()){
      String sqlCommand = "SELECT * FROM matches WHERE id=:id;";
      Match result = con.createQuery(sqlCommand)
        .addParameter("id", id)
        .executeAndFetchFirst(Match.class);
      return result;
    }
  }

  public void delete(){
    String sqlCommand = "DELETE FROM matches WHERE id = :id;";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sqlCommand)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void update(String name, String host, String location, String date, String a1Name, String a2Name, String weight_class, String a1Belt, String a2Belt, String matchurl){
    String sqlCommand = "UPDATE matches SET name=:name, host_org=:host, location=:location, date=:date, athlete_1_name=:a1Name, athlete_2_name=:a2Name, weight_class=:weight_class, athlete_1_belt=:a1Belt, athlete_2_belt=:a2Belt, matchurl=:matchurl WHERE id=:id;";
    System.out.println("ID from update function is " + this.id);
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sqlCommand)
      .addParameter("name", name)
      .addParameter("host", host)
      .addParameter("location", location)
      .addParameter("date", date)
      .addParameter("a1Name", a1Name)
      .addParameter("a2Name", a2Name)
      .addParameter("weight_class", weight_class)
      .addParameter("a1Belt", a1Belt)
      .addParameter("a2Belt", a2Belt)
      .addParameter("matchurl", matchurl)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

}
