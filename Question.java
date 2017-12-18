import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that defines question properties in a game.
 * They are question id, theme, sound file path, texts of questions, correct and wrong answers.
 */

public class Question implements Comparable<Question> {
    private int id;
    private String theme;
    private String soundFile;
    private String textOfQuestion;
    private String correctAnswer;
    private String wrongAnswers1;
    private String wrongAnswers2;
    private String wrongAnswers3;

    Question(int id) {
        this.id = id;
    }

    Question() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme () { return theme;}

    public void setTheme (String theme) { this.theme = theme; }

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }

    public String getTextOfQuestion() {
        return textOfQuestion;
    }

    public void setTextOfQuestion(String textOfQuestion) {
        this.textOfQuestion = textOfQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getwrongAnswers1() {
        return wrongAnswers1;
    }
    public void setwrongAnswers1(String wrongAnswers1){
        this.wrongAnswers1 = wrongAnswers1;
    }

    public String getwrongAnswers2() {
        return wrongAnswers2;
    }
    public void setwrongAnswers2(String wrongAnswers2){
        this.wrongAnswers2 = wrongAnswers2;
    }


    public String getwrongAnswers3() {
        return wrongAnswers3;
    }
    public void setwrongAnswers3(String wrongAnswers3){
        this.wrongAnswers3 = wrongAnswers3;
    }


    @Override
    public int compareTo(Question o) {
        return 0;
    }


    /**
     * Method writes question to Questions.xml file
     */

    public void writeToFile(int ID, String themeFromScene, String questionFromScene, String soundFileName, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) throws IOException {
        FrontPageScene.removeLineFromFile("Questions.xml", "</Questions>");
        BufferedWriter out = new BufferedWriter(new FileWriter("Questions.xml", true));
        out.write("\t<Question>\n" + "\t\t<id>" + ID + "</id>\n" + "\t\t<theme>" + themeFromScene + "</theme>\n" + "\t\t<textOfQuestion>" + questionFromScene + "</textOfQuestion>\n" +
                "\t\t<soundfile>" + soundFileName + "</soundfile>\n" + "\t\t<correctAnswer>" + correctAnswer + "</correctAnswer>\n" +
                "\t\t<wrongAnswer1>" + wrongAnswer1 + "</wrongAnswer1>\n" + "\t\t<wrongAnswer2>" + wrongAnswer2 + "</wrongAnswer2>\n" +
                "\t\t<wrongAnswer3>" + wrongAnswer3 + "</wrongAnswer3>\n" + "\t</Question>\n" + "</Questions>");
        out.newLine();
        out.close();
    }



    }

