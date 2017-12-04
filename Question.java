public class Question implements Comparable<Question>
{
    private int id;
    private String soundFile;
    private String textOfQuestion;
    private String correctAnswer;
    private String dummyAnswers1;
    private String dummyAnswers2;
    private String dummyAnswers3;

    Question(int id) {
        this.id = id;
    }

    Question(){
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoundfile() {
        return soundFile;
    }

    public void setSoundfile(String soundFile) {
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

    public String getDummyAnswers2() {
        return dummyAnswers2;
    }

    public String getDummyAnswers3() {
        return dummyAnswers3;
    }

    public void setDummyAnswers(String dummyAnswers1, String dummyAnswers2, String dummyAnswers3) {
        this.dummyAnswers1 = dummyAnswers1;
        this.dummyAnswers2 = dummyAnswers2;
        this.dummyAnswers3 = dummyAnswers3;

    }

    @Override
    public int compareTo(Question o) {
        return 0;
    }
}

