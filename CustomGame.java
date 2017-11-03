import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class CustomGame {



    CustomGame() {
        JFrame frame = new JFrame(); //Making a frame
        frame.setSize(900, 700); // size of frame
        JButton addC = new JButton("Add Card"); // Adding a button
        addC.setBounds(350, 50, 200, 50);
        frame.add(addC);
        frame.setLayout(null);
        frame.setVisible(true);

        JButton yourC = new JButton("Your Cards");
        frame.add(yourC);
        yourC.setBounds(350, 150, 200, 50);

        JButton uNS = new JButton("Upload new sound");
        frame.add(uNS);
        uNS.setBounds(350, 250, 200, 50);

        JButton quickPlay = new JButton("Next"); // Making the button "next" go to the 'quick play' page.
        quickPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuickPlayButton qPB = new QuickPlayButton();

            }
        });
        frame.add(quickPlay);
        quickPlay.setBounds(500, 500, 200, 50);

//        frame.setLayout(null);
//        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        CustomGame test = new CustomGame();
        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  f.getContentPane().add(test.process1());
        f.setSize(500,500);
        f.setLocation(200,200);
 //       f.setVisible(true);
 //       new CustomGame();

    }
}
