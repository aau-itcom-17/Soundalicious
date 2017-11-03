
import javax.swing.*;


public class Menu {



    Menu() {
        /* JFrame is a top level container (window)
         * where we would be adding our button
         */
        JFrame frame = new JFrame();

        // Creating Button          
        JButton b = new JButton("Quick Play");
        /* This method specifies the location and size
         * of button. In method setBounds(x, y, width, height)
         * x,y) are coordinates from the top left
         * corner and remaining two arguments are the width
         * and height of the button.
         */
        b.setBounds(350, 50, 200, 50);

        //Adding button onto the frame
        frame.add(b);

        // Setting Frame size. This is the window size
        frame.setSize(900, 1500);

        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton a = new JButton("Costum game");
        a.addActionListener(new ActionListener() { //Make the custom game button go to the Custom Game java class. 
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomGame qPB = new CustomGame();
            }
        });

        frame.add(a);

        a.setBounds(350, 150, 200, 50);


        frame.setLayout(null);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton c = new JButton("Sign in / create user");

        frame.add(c);

        c.setBounds(350, 250, 200, 50);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






    }

        public static void main(String[] args) {
            new Menu();

        }

}



















