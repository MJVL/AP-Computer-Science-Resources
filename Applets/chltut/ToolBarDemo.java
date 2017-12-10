import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToolBarDemo extends JFrame {

        protected JTextArea textArea;
        protected String newline="\n";

        public ToolBarDemo() {
                super("ToolBarDemo");
                addWindowListener(new WindowAdapter() {
                        public void windowclosing(WindowEvent e) {
                                System.exit(0);
                        }
                });

                JToolBar toolBar = new JToolBar();
                addButtons(toolBar);
                textArea = new JTextArea(5, 30);
                JScrollPane scrollPane = new JScrollPane(textArea);

                JPanel contentPane = new JPanel() ;
                contentPane.setLayout(new BorderLayout());
                contentPane.setPreferredSize(new Dimension( 400, 100));
                contentPane.add(toolBar, BorderLayout.NORTH);
                contentPane.add(scrollPane, BorderLayout.CENTER);
                setContentPane(contentPane);

}

protected void addButtons(JToolBar toolbar) {

        JButton button = null;

        button = new JButton(new ImageIcon("images/left.gif"));
        button.setToolTipText("This is the left button");
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        displayResult("Action for the first button");
                }
        });
        toolbar.add(button);

        button = new JButton(new ImageIcon("images/middle.gif"));
        button.setToolTipText("This is the middle button");
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        displayResult("Action for the second  button");
                }
        });
        toolbar.add(button);

        button = new JButton(new ImageIcon("images/right.gif"));
        button.setToolTipText("This is the right button");
        button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        displayResult("Action for the third button");
                }
        });
        toolbar.add(button);

}

protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
}

public static void main(String [] args) {
        ToolBarDemo frame = new ToolBarDemo();
        frame.pack();
        frame.setVisible(true);

        }

}
