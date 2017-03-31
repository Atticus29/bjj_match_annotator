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
      model.put("origin", "new");
      model.put("template", "templates/match-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/new/moves/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String origin = request.queryParams("origin");
      if (!origin.equals("add-another")) {
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
        model.put("match", newMatch);
      // } else {
      //   model.put("match", request.queryParams("match"));
      }
      model.put("origin", origin);
      model.put("template", "templates/move-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      // System.out.println("all query params are " + request.queryParams());
      String origin = request.queryParams("origin");
      int matchid = Integer.parseInt(request.params(":matchid"));
      System.out.println("origin is " + origin);
      if(!origin.equals("update")){
        String name = request.queryParams("move-name");
        String description = request.queryParams("description");
        String difficulty = request.queryParams("difficulty");
        Move newMove = new Move(matchid, name, description, difficulty);
        newMove.save();
        model.put("moves", Match.find(matchid).getMoves(matchid));
      } else{
        // Match match = Match.find(matchid);
        String name = request.queryParams("match-name");
        String hostOrg = request.queryParams("host-org");
        String location = request.queryParams("location");
        String date = request.queryParams("date");
        String a1Name = request.queryParams("athlete-1-name");
        String a2Name = request.queryParams("athlete-2-name");
        String weightClass = request.queryParams("weight-class");
        String a1Belt = request.queryParams("athlete-1-belt");
        String a2Belt = request.queryParams("athlete-2-belt");
        String matchURL = request.queryParams("match-url");
        match.update(name, hostOrg, location, date, a1Name, a2Name, weightClass, a1Belt, a2Belt, matchURL);
      }
      model.put("match", Match.find(matchid));
      model.put("template", "templates/match.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/matches/:matchid/edit", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("origin", "update");
      int matchid = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchid);
      model.put("match", match);
      model.put("template", "templates/match-form.vtl");
      // response.redirect("/matches/new/moves/new");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/matches/:matchid", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchidInt);
      model.put("match", match);
      model.put("moves", match.getMoves(matchidInt));
      model.put("template", "templates/match.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid/moves/:moveid/delete", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchidInt);
      int moveID = Integer.parseInt(request.queryParams("moveid"));
      Move move = Move.find(moveID);
      move.delete();
      response.redirect("/matches/" + match.getId());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid/delete", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchidInt);
      match.delete();
      response.redirect("/");
      // model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid/moves/:moveid/edit", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchidInt);
      model.put("match", match);
      int moveID = Integer.parseInt(request.queryParams("moveid"));
      Move move = Move.find(moveID);
      model.put("origin", "edit");
      model.put("move", move);
      model.put("template", "templates/move-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid/edit", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchidInt);
      model.put("origin", "edit");
      model.put("match", match);
      model.put("template", "templates/match-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/matches/:matchid/moves/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      Match match = Match.find(matchidInt);
      model.put("origin", "new-move");
      // model.put("origin", request.queryParams("origin"));
      model.put("match", match);
      model.put("template", "templates/move-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("matches/:matchid/moves/:moveid", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int matchidInt = Integer.parseInt(request.params(":matchid"));
      int moveidInt = Integer.parseInt(request.params(":moveid"));
      Move move = Move.find(moveidInt);
      model.put("match", Match.find(matchidInt));
      model.put("move", move);
      model.put("template", "templates/move.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

}
