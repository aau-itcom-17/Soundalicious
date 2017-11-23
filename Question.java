import java.io.Serializable;

public class Question implements Comparable<Question> {

    private int id;
    private String questionName;
    private String soundFilePath;
    private String correctAnswer;
    private String[] dummyAnswers;

    Question(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return questionName;
    }

    public void setName(String name) {
        this.questionName = name;
    }

    public String getText() {
        return soundFilePath;
    }

    public void setText(String text) {
        this.soundFilePath = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String[] getDummyAnswers() {
        return dummyAnswers;
    }

    public void setDummyAnswers(String[] dummyAnswers) {
        this.dummyAnswers = dummyAnswers;
    }

    @Override
    public int compareTo(Question o) {
        return 0;
    }
}
