import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

/**
 * This class deal with the questions being read from the file to the list and generating a list of the questions for the game.
 * It can get defined number of questions either from the whole list or just by the specific theme.
 */

public class Questions {

    private SecureRandom random;
    private static Question temp;

    Questions(List<Question> questions, List<Question> rQuestions) throws IOException, SAXException, ParserConfigurationException {
        random = new SecureRandom();
        readQuestionsFromFile(questions, rQuestions);
    }

    /**
     * Reading questions from the .xml file to the list defined by Question.java class
     */
    public static void readQuestionsFromFile(List<Question> questions, List<Question> rQuestions) throws ParserConfigurationException, IOException, SAXException {

        File file = new File("Questions.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList list = document.getElementsByTagName("Question");

        for (int i = 0; i < list.getLength(); i++) {
            temp = new Question();

            temp.setId(Integer.parseInt(document.getElementsByTagName("id").item(i).getTextContent()));
            temp.setTheme(document.getElementsByTagName("theme").item(i).getTextContent());
            temp.setTextOfQuestion(document.getElementsByTagName("textOfQuestion").item(i).getTextContent());
            temp.setSoundFile(document.getElementsByTagName("soundfile").item(i).getTextContent());
            temp.setCorrectAnswer(document.getElementsByTagName("correctAnswer").item(i).getTextContent());
            temp.setwrongAnswers1(document.getElementsByTagName("wrongAnswer1").item(i).getTextContent());
            temp.setwrongAnswers2(document.getElementsByTagName("wrongAnswer2").item(i).getTextContent());
            temp.setwrongAnswers3(document.getElementsByTagName("wrongAnswer3").item(i).getTextContent());
            // System.out.println(temp.getCorrectAnswer());

            questions.add(temp);
        }
    }

    /**
     * Gets all the questions from the general list and saves it to themeQuestions list by theme used in the parameters.
     */
    public static void getThemeQuestions(List<Question> questions, List<Question> rQuestions, List<Question> themeQuestions, String selection) throws ParserConfigurationException, SAXException, IOException {
        themeQuestions.clear();

        for (int i = 0; i < questions.size(); i++) {
            if (selection.equals(Constants.topicText1)) {
                if (questions.get(i).getTheme().equals(Constants.topicText1)) {
                    themeQuestions.add(questions.get(i));
                }
            } else if (selection.equals(Constants.topicText2)) {
                if (questions.get(i).getTheme().equals(Constants.topicText2)) {
                    themeQuestions.add(questions.get(i));
                }
            }

        }
        rQuestions.clear();
        Question q;
        while (themeQuestions.size() != rQuestions.size()) {
            SecureRandom r = new SecureRandom();

            q = themeQuestions.get(r.nextInt(themeQuestions.size()));

            if (!rQuestions.contains(q)) {  // should implement the Comparable interface.
                rQuestions.add(q);
            }
        }
    }

    /**
     * Gets defined number of random questions from the list and saves it to the new list.
     */
    public static void getRandomQuestions(List<Question> questions, List<Question> rQuestions, List<Question> themeQuestions) throws ParserConfigurationException, SAXException, IOException {

        rQuestions.clear();
        Question q;
        while (questions.size() != rQuestions.size()) {
            SecureRandom r = new SecureRandom();


            q = questions.get(r.nextInt(questions.size()));

            if (!rQuestions.contains(q)) {  // should implement the Comparable interface.
                rQuestions.add(q);
            }
        }
    }
}






