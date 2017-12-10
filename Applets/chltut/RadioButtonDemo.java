import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonDemo extends JPanel  {
	static JFrame frame;
	static String birdString = "Bird";
	static String catString = "Cat";
	static String rabbitString = "Rabbit";
	JLabel picture;

	public RadioButtonDemo() {
		JRadioButton birdButton = new JRadioButton(birdString);
		birdButton.setMnemonic('b');
		birdButton.setActionCommand(birdString);
		birdButton.setSelected(true);

		JRadioButton catButton = new JRadioButton(catString);
		catButton.setMnemonic('c');
		catButton.setActionCommand(catString);

		JRadioButton rabbitButton = new JRadioButton(rabbitString);
		rabbitButton.setMnemonic('r');
		rabbitButton.setActionCommand(rabbitString);

		ButtonGroup group = new ButtonGroup();
		group.add(birdButton);
		group.add(catButton);
		group.add(rabbitButton);

		RadioListener myListener = new RadioListener();
		birdButton.addActionListener(myListener);
		catButton.addActionListener(myListener);
		rabbitButton.addActionListener(myListener);

		picture = new JLabel(new ImageIcon("images/" + birdString + ".gif"));
		picture.setPreferredSize(new Dimension(177, 122));

		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,1));
		radioPanel.add(birdButton);
		radioPanel.add(catButton);
		radioPanel.add(rabbitButton);

		setLayout(new BorderLayout());
		add(radioPanel, BorderLayout.WEST);
		add(picture, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

	}
		
		
class RadioListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		picture.setIcon(new ImageIcon("images/" + e.getActionCommand() + ".gif"));

	}
}

public static void main(String args[]) {
	frame = new JFrame("RadioButtonsDemo");
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	});
	frame.getContentPane().add(new RadioButtonDemo(), BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);

}

}

