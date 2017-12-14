import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Question implements Comparable<Question> {
    private int id;
    private String theme;
    private String soundFile;
    private String textOfQuestion;
    private String correctAnswer;
    private String dummyAnswers1;
    private String dummyAnswers2;
    private String dummyAnswers3;

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

    public String getDummyAnswers1() {
        return dummyAnswers1;
    }
    public void setDummyAnswers1(String dummyAnswers1){
        this.dummyAnswers1 = dummyAnswers1;
    }

    public String getDummyAnswers2() {
        return dummyAnswers2;
    }
    public void setDummyAnswers2(String dummyAnswers2){
        this.dummyAnswers2 = dummyAnswers2;
    }


    public String getDummyAnswers3() {
        return dummyAnswers3;
    }
    public void setDummyAnswers3(String dummyAnswers3){
        this.dummyAnswers3 = dummyAnswers3;
    }


    @Override
    public int compareTo(Question o) {
        return 0;
    }

    public void writeToFile(int ID, String themeFromScene, String questionFromScene, String soundFileName, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) throws IOException {
        FrontPageScene.removeLineFromFile("Questions.xml", "</Questions>");
        BufferedWriter out = new BufferedWriter(new FileWriter("Questions.xml", true));
        out.write("\t<Question>\n" + "\t\t<id>" + ID + "</id>\n" + "\t\t<theme>" + themeFromScene + "</theme>\n" + "\t\t<textOfQuestion>" + questionFromScene + "</textOfQuestion>\n" +
                "\t\t<soundfile>" + soundFileName + "</soundfile>\n" + "\t\t<correctAnswer>" + correctAnswer + "</correctAnswer>\n" +
                "\t\t<dummyAnswer1>" + wrongAnswer1 + "</dummyAnswer1>\n" + "\t\t<dummyAnswer2>" + wrongAnswer2 + "</dummyAnswer2>\n" +
                "\t\t<dummyAnswer3>" + wrongAnswer3 + "</dummyAnswer3>\n" + "\t</Question>\n" + "</Questions>");
        out.newLine();
        out.close();
    }



    }

