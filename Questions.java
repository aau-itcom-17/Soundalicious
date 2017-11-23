import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questions;
    SecureRandom r;

    Questions() {
        readQuestionsFromFile();
        r = new SecureRandom();
    }

    private void readQuestionsFromFile() {
        // I read the questions from the file here
    }

    private Question getRandomQuestion() {
        return questions.get(r.nextInt(questions.size()));
    }

    List<Question> getRandomQuestions(int n) {
        List<Question> rQuestions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Question q = getRandomQuestion();
            if (!rQuestions.contains(q)) {  // should implement the Comparable interface.
                rQuestions.add(q);
            }
        }

        return rQuestions;
    }

    void saveQuestionsOnFile() {
        // write the file...
    }
}
