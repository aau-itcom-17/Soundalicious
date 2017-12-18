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

    /**
     * Gets the first place winner's index when game is finished.
     */

    public static int getWinner()
    {
        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() > max) {
                max = Main.teams.get(i).getPointScore();
                maxPos = i;
            }
        }
        return maxPos;
    }

    /**
     * Gets the second place team's index when game is finished.
     */
    public static int get2ndPlace()
    {

        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() > max) {
                max = Main.teams.get(i).getPointScore();
                maxPos = i;
            }
            if(Main.teams.get(i).getPointScore() > secondLargest && Main.teams.get(i).getPointScore() != max) {
                secondLargest = Main.teams.get(i).getPointScore();
                secondPos = i;
            }
        }
        return secondPos;
    }

    /**
     * Gets the third place team's index when game is finished.
     */
    public static int get3rdPlace()
    {
        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() > max) {
                max = Main.teams.get(i).getPointScore();
                maxPos = i;
            }
            if(Main.teams.get(i).getPointScore() > secondLargest && Main.teams.get(i).getPointScore() != max) {
                secondLargest = Main.teams.get(i).getPointScore();
                secondPos = i;
            }
            if(Main.teams.get(i).getPointScore() > thirdLargest && Main.teams.get(i).getPointScore() != secondLargest && Main.teams.get(i).getPointScore() != max) {
                thirdLargest = Main.teams.get(i).getPointScore();
                thirdPos = i;
            }
        }
        return thirdPos;
    }

    /**
     * Gets index of the team that got the same score?
     */
    public static int getSameScore() {
        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() > max) {
                max = Main.teams.get(i).getPointScore();
                maxPos = i;
            }
            if (Main.teams.get(i).getPointScore() > secondLargest && Main.teams.get(i).getPointScore() != max) {
                secondLargest = Main.teams.get(i).getPointScore();
                secondPos = i;
            }
            if (Main.teams.get(i).getPointScore() > thirdLargest && Main.teams.get(i).getPointScore() != secondLargest && Main.teams.get(i).getPointScore() != max) {
                thirdLargest = Main.teams.get(i).getPointScore();
                thirdPos = i;
            }
        }
        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() == max && i != maxPos || Main.teams.get(i).getPointScore() == secondLargest && i != secondPos
                    || Main.teams.get(i).getPointScore() == thirdLargest && i != thirdPos) {
                sameScorePos = i;
            }
        }
        return sameScorePos;
    }

    /**
     * Gets index of the teams that got the same score
     */
    public static int getSameScoreForThree() {
        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() > max) {
                max = Main.teams.get(i).getPointScore();
                maxPos = i;
            }
            if (Main.teams.get(i).getPointScore() > secondLargest && Main.teams.get(i).getPointScore() != max) {
                secondLargest = Main.teams.get(i).getPointScore();
                secondPos = i;
            }
            if (Main.teams.get(i).getPointScore() > thirdLargest && Main.teams.get(i).getPointScore() != secondLargest && Main.teams.get(i).getPointScore() != max) {
                thirdLargest = Main.teams.get(i).getPointScore();
                thirdPos = i;
            }
        }
        for (int i = 0; i < Main.teams.size(); i++) {
            if (Main.teams.get(i).getPointScore() == max && i != getSameScore() && i != maxPos
                    || Main.teams.get(i).getPointScore() == secondLargest && i != getSameScore() && i != secondPos
                    || Main.teams.get(i).getPointScore() == thirdLargest && i != getSameScore() && i != thirdPos) {
                sameScoreForThreePos = i;
            }
        }
        return sameScoreForThreePos;
    }
}
