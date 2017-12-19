/**
 * Class deals with team scores and names.
 * Mostly used for leaderbord.
 */
public class Team {

    static int max = Integer.MIN_VALUE;
    static int maxPos = 0;
    static int secondLargest = Integer.MIN_VALUE;
    static int secondPos = 0;
    static int thirdLargest = Integer.MIN_VALUE;
    static int thirdPos = 0;
    static int sameScorePos = 0;
    static int sameScoreForThreePos = -1;
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
