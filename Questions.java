import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions
{
    static SecureRandom r;
    static Question temp = new Question();

    Questions(List<Question> questions) throws IOException, SAXException, ParserConfigurationException
    {
        readQuestionsFromFile(questions);
        r = new SecureRandom();
    }


    public static void readQuestionsFromFile(List<Question> questions) throws ParserConfigurationException, IOException, SAXException {


        File file = new File("Questions.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        temp.setId(Integer.parseInt(document.getElementsByTagName("id").item(0).getTextContent()));

        temp.setTextOfQuestion(document.getElementsByTagName("textOfQuestion").item(0).getTextContent());
        temp.setSoundfile(document.getElementsByTagName("soundfile").item(0).getTextContent());
        temp.setCorrectAnswer(document.getElementsByTagName("correctAnswer").item(0).getTextContent());
        temp.setDummyAnswers((document.getElementsByTagName("dummyAnswer1").item(0).getTextContent()), document.getElementsByTagName("dummyAnswer2").item(0).getTextContent(), document.getElementsByTagName("dummyAnswer3").item(0).getTextContent());

        questions.add(temp);
    }


    public static List<Question> getRandomQuestions(int n, List<Question> questions) throws ParserConfigurationException, SAXException, IOException {
        List<Question> rQuestions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            readQuestionsFromFile(questions);
            Question q = Main.questions.get(r.nextInt(Main.questions.size()));
            System.out.println(q.getCorrectAnswer());
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