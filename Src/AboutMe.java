import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class AboutMe extends JPanel {

//	Information Info = new Information();
	/**
	 * Create the panel.
	 */
	public AboutMe() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("About Me");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(50, 10));
		add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(50, 10));
		add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Information.getAboutusr());
		add(textArea, BorderLayout.CENTER);

	}

}
