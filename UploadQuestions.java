import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UploadQuestions {
    private int ID;
    private String questionFromScene;
    private String soundFileName;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestionFromScene() {
        return questionFromScene;
    }

    public void setQuestionFromScene(String questionFromScene) {
        this.questionFromScene = questionFromScene;
    }

    public String getSoundFileName() {
        return soundFileName;
    }

    public void setSoundFileName(String soundFileName) {
        this.soundFileName = soundFileName;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public void writeToFile(int ID, String questionFromScene, String soundFileName, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter("Questions.xml", true));
        out.write("<Question>\n" + "<id>" + ID + "</id>\n" + "<textOfQuestion>" + questionFromScene + "</textOfQuestion>\n" +
                "<soundfile>" + soundFileName + "</soundfile>\n" + "<correctAnswer>" + correctAnswer + "</correctAnswer>\n" +
                "<dummyAnswer1>" + wrongAnswer1 +"</dummyAnswer1>\n" + "<dummyAnswer2>" + wrongAnswer2 + "</dummyAnswer2>\n" +
                "<dummyAnswer3>" + wrongAnswer3 + "</dummyAnswer3>\n" + "</Question>\n");
        out.newLine();
        out.close();
    }

}
