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

  public Match(String name, String host, String location, String date, String a1Name, String a2Name, String weight_class, String a1Belt, String a2Belt) {
    this.name = name;
    this.host_org = host;
    this.location = location;
    this.date = date;
    this.athlete_1_name = a1Name;
    this.athlete_2_name = a2Name;
    this.weight_class = weight_class;
    this.athlete_1_belt = a1Belt;
    this.athlete_2_belt = a2Belt;
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

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sqlCommand = "INSERT INTO matches (name, host_org, location, date, athlete_1_name, athlete_2_name, weight_class, athlete_1_belt, athlete_2_belt) VALUES (:name, :host_org, :location, :date, :athlete_1_name, :athlete_2_name, :weight_class, :athlete_1_belt, :athlete_2_belt);";
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

}
