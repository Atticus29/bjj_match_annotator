import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("matches", Match.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/matches/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/match-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/new/moves/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("match-name");
      String host = request.queryParams("host-org");
      String location = request.queryParams("location");
      String date = request.queryParams("date");
      String athleteOneName = request.queryParams("athlete-1-name");
      String athleteTwoName = request.queryParams("athlete-2-name");
      String weightClass = request.queryParams("weight-class");
      String aOneBelt = request.queryParams("athlete-1-belt");
      String aTwoBelt = request.queryParams("athlete-2-belt");
      String matchUrl = request.queryParams("match-url");
      Match newMatch = new Match(name, host, location, date, athleteOneName, athleteTwoName, weightClass, aOneBelt, aTwoBelt, matchUrl);
      newMatch.save();
      String origin = request.queryParams("origin");
      model.put("match", newMatch);
      model.put("origin", origin);
      model.put("template", "templates/move-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      System.out.println("all query params are " + request.queryParams());
      int matchid = Integer.parseInt(request.params(":matchid"));
      String name = request.queryParams("move-name");
      String description = request.queryParams("description"
      );
      String difficulty = request.queryParams("difficulty");
      Move newMove = new Move(matchid, name, description, difficulty);
      newMove.save();
      model.put("matchid", matchid);
      model.put("template", "templates/match.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
