import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.SwingUtilities;


public class QuickPlayButton {

    private JFrame frame;
    private JPanel panel;
    private JTextArea jTextArea;
    private JButton butt;

    QuickPlayButton() {
        createGUI();
        process1();
    }

    public void process1() {
        jTextArea.setText("Sup friends");
        frame.setVisible(true);
    }

    public void createGUI() {
        butt = new JButton("QuickPlay");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        jTextArea = new JTextArea(20, 20);
        panel.add(jTextArea);
        panel.add(butt);
        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                final JPanel pan = new JPanel();
                JButton but = new JButton("Team1");
                pan.add(but);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.add(pan);
                        butt.setVisible(false);
                        jTextArea.setVisible(false);
                        pan.revalidate();
                        pan.repaint();
                    }
                });
            }
        });
        frame.add(panel);
        frame.pack();
        frame.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuickPlayButton();
            }
        });
    }
}
