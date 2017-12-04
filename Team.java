public class Team {

    private int ID;
    private int pointScore;
    private String teamName;

   public Team(String teamName, int ID, int pointScore){

   }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPointScore() {
        return pointScore;
    }

    public void setPointScore(int pointScore) {
        this.pointScore = pointScore;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
