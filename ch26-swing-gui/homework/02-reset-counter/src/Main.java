import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
    static int count = 0;
    static JLabel countLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Counter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            countLabel = new JLabel("Count: 0", JLabel.CENTER);
            JButton up = new JButton("+1");
            JButton reset = new JButton("Reset");

            up.addActionListener(event -> {
                count++;
                countLabel.setText("Count: " + count);
            });
            reset.addActionListener(event -> {
                count = 0;
                countLabel.setText("Count: " + count);
            });

            JPanel buttons = new JPanel(new GridLayout(1, 2));
            buttons.add(up);
            buttons.add(reset);

            frame.setLayout(new BorderLayout());
            frame.add(countLabel, BorderLayout.CENTER);
            frame.add(buttons, BorderLayout.SOUTH);
            frame.setSize(260, 140);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
