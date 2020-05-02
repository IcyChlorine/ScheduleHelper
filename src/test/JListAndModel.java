package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import StarSky.Task;

public class JListAndModel extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new JListAndModel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 300);
        frame.setVisible(true);
    }

	private JLabel label1;
    private JList list1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;

    DefaultListModel<Task> listModel1 = new DefaultListModel<>();

    public JListAndModel() {
		super("Form1");

		label1 = new JLabel("some items:");
		getContentPane().add(label1, BorderLayout.SOUTH);
		list1 = new JList();
		getContentPane().add(list1, BorderLayout.CENTER);
		panel1 = new JPanel();
		getContentPane().add(panel1, BorderLayout.SOUTH);

		textField1 = new JTextField(20);
		panel1.add(textField1);
		button1 = new JButton("add it");
		panel1.add(button1);
		button2 = new JButton("remove first");
		panel1.add(button2);



        list1.setModel(listModel1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField1.getText();
				textField1.setText("");
                listModel1.addElement(new Task(text));
            }
        });

		button2.addActionListener(e -> {
            listModel1.remove(1);
        });
    }
}
