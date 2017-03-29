import org.sql2o.*;

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

}
