import com.codebird.QuickPlay;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;

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

        JButton backP1 = new JButton("Back");
        backP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
            }
        });
        frame.add(backP1);
        backP1.setBounds(50, 500, 100, 20);



        JButton quickPlay = new JButton("Next"); // Making the button "next" go to the 'quick play' page.
        // We have to make the quick play page overtake the current page, instead of opening a new quick play page.
        quickPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JPanel pan = new JPanel();
                QuickPlayButton qPB = new QuickPlayButton();
            }
        });
        frame.add(quickPlay);
        quickPlay.setBounds(750, 500, 100, 20);

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
