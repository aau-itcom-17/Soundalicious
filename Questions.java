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
import java.util.Random;

public class Questions {

    static Random random;
    static Question temp;

    Questions(List<Question> questions, List<Question> rQuestions) throws IOException, SAXException, ParserConfigurationException {
        //r = new SecureRandom();
        random = new Random();
        readQuestionsFromFile(questions, rQuestions);


    }


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
            temp.setDummyAnswers1(document.getElementsByTagName("dummyAnswer1").item(i).getTextContent());
            temp.setDummyAnswers2(document.getElementsByTagName("dummyAnswer2").item(i).getTextContent());
            temp.setDummyAnswers3(document.getElementsByTagName("dummyAnswer3").item(i).getTextContent());
            // System.out.println(temp.getCorrectAnswer());

            questions.add(temp);
        }
    }


    public static void getThemeQuestions(List<Question> questions, List<Question> rQuestions, List<Question> themeQuestions, String selection) throws ParserConfigurationException, SAXException, IOException {
        System.out.println(selection + "!!!!!!!!!!!!!!!!!");

        for (int i = 0; i < questions.size(); i++) {
            if (selection.equals(Constants.topicText1)) {
                if (questions.get(i).getTheme().equals("Theme1")) {
                    themeQuestions.add(questions.get(i));
                    System.out.println(selection + questions.get(i).getTheme());
                }
            } else if (selection.equals(Constants.topicText2)) {
                if (questions.get(i).getTheme().equals("Theme2")) {
                    themeQuestions.add(questions.get(i));
                    System.out.println(selection + questions.get(i).getCorrectAnswer());
                }
            } else if (selection.equals(Constants.topicText3)) {
                if (questions.get(i).getTheme().equals("Theme3")) {
                    themeQuestions.add(questions.get(i));
                    System.out.println(selection + questions.get(i).getDummyAnswers1());
                }
            } else if (selection.equals(Constants.topicText4)) {
                if (questions.get(i).getTheme().equals("Theme4")) {
                    themeQuestions.add(questions.get(i));
                    System.out.println(selection + questions.get(i).getId());
                }
            } else if (selection.equals(Constants.topicText5)) {
                if (questions.get(i).getTheme().equals("Theme5")) {
                    themeQuestions.add(questions.get(i));
                    System.out.println(selection + questions.get(i).getTheme());
                }
            }

        }

        /*

            if (CustomGameScene.radioBut1.isSelected()) {
                    for (int i = 0; i < questions.size(); i++) {
                        while (questions.get(i).getTheme().equals("Theme1")) {
                            themeQuestions.add(questions.get(i));
                        }
                    }
            } else if (CustomGameScene.radioBut2.isSelected()) {
                for (int i = 0; i < questions.size(); i++) {
                    while (questions.get(i).getTheme().equals("Theme2")) {
                        themeQuestions.add(questions.get(i));
                    }
                    System.out.println("I'm in getThemeQuestions!!");
                }
            } else if (CustomGameScene.radioBut3.isSelected()) {
                    for (int i = 0; i < questions.size(); i++) {
                        while (questions.get(i).getTheme().equals("Theme3")) {
                            themeQuestions.add(questions.get(i));
                        }
                    }
            } else if (CustomGameScene.radioBut4.isSelected()) {
                for (int i = 0; i < questions.size(); i++) {
                while (questions.get(i).getTheme().equals("Theme4")) {
                    themeQuestions.add(questions.get(i));
                }
                    }
            } else if (CustomGameScene.radioBut5.isSelected()) {
                for (int i = 0; i < questions.size(); i++) {
                while (questions.get(i).getTheme().equals("Theme5")) {
                    themeQuestions.add(questions.get(i));
                }
            }
        }
        */
        rQuestions.clear();
        Question q;
        while (themeQuestions.size() != rQuestions.size()) {
            SecureRandom r = new SecureRandom();

            //readQuestionsFromFile(questions);
            q = themeQuestions.get(r.nextInt(themeQuestions.size()));

            if (!rQuestions.contains(q)) {  // should implement the Comparable interface.
                rQuestions.add(q);
            }
        }
    }


        /*
        Question t;

        while (questions.size() != rQuestions.size()) {
            SecureRandom r = new SecureRandom();


            //readQuestionsFromFile(questions);
            q = questions.get(r.nextInt(questions.size()));

            if (!rQuestions.contains(q)) {  // should implement the Comparable interface.
                rQuestions.add(q);
            }
        }
        */




    public static void getRandomQuestions(List<Question> questions, List<Question> rQuestions, List<Question> themeQuestions) throws ParserConfigurationException, SAXException, IOException {

        rQuestions.clear();
        Question q;
            while (questions.size() != rQuestions.size()) {
                SecureRandom r = new SecureRandom();


                //readQuestionsFromFile(questions);
                q = questions.get(r.nextInt(questions.size()));

                if (!rQuestions.contains(q)) {  // should implement the Comparable interface.
                    rQuestions.add(q);
                }
            }
        }
    }






