package StarSky;

import java.io.*;
import StarSky.myutil.MyDate;
import java.awt.EventQueue;


import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import com.alibaba.fastjson.JSON;

import javax.swing.event.ListSelectionEvent;

public class MainFrame {

	private JFrame frame;
	
	private JTabbedPane tabbedPane;
	private JPanel panelAddTask;
	private JPanel panelBrowseTask;
	private JPanel panelDoneTask;
	
	private JList listTasks;
	private JList listTasksDone;
	private DefaultListModel<Task> listModelTasks;
	private DefaultListModel<Task> listModelTasksDone;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelAddTask = new JPanel();
		panelBrowseTask = new JPanel();
		panelDoneTask = new JPanel();
		panelAddTask.setLayout(null);
		panelBrowseTask.setLayout(null);
		panelDoneTask.setLayout(null);
		tabbedPane.add("新任务",panelAddTask);
		tabbedPane.add("已添加任务",panelBrowseTask);
		tabbedPane.add("已完成任务",panelDoneTask);
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				load();
			}
			@Override
	        public void windowClosing(WindowEvent e) {
				save();
	        }	
			
		});
		
		initializePanelNewTask();
		initializePanelBrowseTask();
		initializePanelDoneTask();

	}
	private void initializePanelNewTask() {

		JLabel lblTextContent = new JLabel("Content");
		lblTextContent.setFont(new Font("宋体", Font.PLAIN, 17));
		lblTextContent.setBounds(114, 47, 232, 18);
		panelAddTask.add(lblTextContent);
		
		JTextArea textAreaContent = new JTextArea();
		textAreaContent.setBounds(127, 78, 355, 83);
		panelAddTask.add(textAreaContent);
		
		JCheckBox chkboxDDL = new JCheckBox("DDL");
		chkboxDDL.setBounds(89, 178, 133, 27);
		panelAddTask.add(chkboxDDL);
		
		JTextField textFieldDDLYear = new JTextField();
		textFieldDDLYear.setBounds(136, 242, 86, 24);
		panelAddTask.add(textFieldDDLYear);
		textFieldDDLYear.setColumns(10);
		
		JTextField textFieldDDLMonth = new JTextField();
		textFieldDDLMonth.setBounds(243, 242, 34, 24);
		panelAddTask.add(textFieldDDLMonth);
		textFieldDDLMonth.setColumns(10);
		
		JTextField textFieldDDLDay = new JTextField();
		textFieldDDLDay.setBounds(301, 242, 34, 24);
		panelAddTask.add(textFieldDDLDay);
		textFieldDDLDay.setColumns(10);
		
		MyDate today=new MyDate();
		textFieldDDLYear.setText(""+today.getYear());
		textFieldDDLMonth.setText(""+today.getMonth());
		textFieldDDLDay.setText(""+today.getDay());
		
		JLabel lblDateHyphen1 = new JLabel("-");
		lblDateHyphen1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblDateHyphen1.setBounds(225, 245, 15, 18);
		panelAddTask.add(lblDateHyphen1);
		
		JLabel lblDateHyphen2 = new JLabel("-");
		lblDateHyphen2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblDateHyphen2.setBounds(280, 245, 15, 18);
		panelAddTask.add(lblDateHyphen2);
		
		//初始化不可用
		textFieldDDLYear.setEnabled(false);
		textFieldDDLMonth.setEnabled(false);
		textFieldDDLDay.setEnabled(false);
		lblDateHyphen1.setEnabled(false);
		lblDateHyphen2.setEnabled(false);
		
		chkboxDDL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b=chkboxDDL.isSelected();
				textFieldDDLYear.setEnabled(b);
				textFieldDDLMonth.setEnabled(b);
				textFieldDDLDay.setEnabled(b);
				lblDateHyphen1.setEnabled(b);
				lblDateHyphen2.setEnabled(b);
			}
		});
		
		JButton btnAddTask = new FairButton("Add");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textAreaContent.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "任务内容不能为空！");
					return;
				}
				//有ddl的情形
				if(chkboxDDL.isSelected()) {
					MyDate ddl;
					try {
						ddl=new MyDate(
								Integer.parseInt(textFieldDDLYear.getText()),
								Integer.parseInt(textFieldDDLMonth.getText()),
								Integer.parseInt(textFieldDDLDay.getText())
								);
					}catch(IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(frame, "无效的日期！");
						return;
					}
					listModelTasks.addElement(
							new Task(textAreaContent.getText(),ddl)
							);
					
				}else {//没有ddl的情形
					listModelTasks.addElement(new Task(textAreaContent.getText()));
				}
				textAreaContent.setText("");
			}
		});
		btnAddTask.setBounds(280, 327, 113, 27);
		panelAddTask.add(btnAddTask);
		
		JButton btnReset = new FairButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaContent.setText("");
				MyDate today=new MyDate();
				textFieldDDLYear.setText(""+today.getYear());
				textFieldDDLMonth.setText(""+today.getMonth());
				textFieldDDLDay.setText(""+today.getDay());
			}
		});
		btnReset.setBounds(425, 327, 113, 27);
		panelAddTask.add(btnReset);
	}
	
	private void initializePanelBrowseTask() {
		listTasks = new JList();
		
		listTasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTasks.setBounds(59, 62, 231, 238);
		panelBrowseTask.add(listTasks);
		
		listModelTasks=new DefaultListModel<Task>();
		listTasks.setModel(listModelTasks);
		
		//listModelTasks.addElement(new Task("some content",new MyDate()));
		
		JLabel lblTextContent = new JLabel("Content:");
		lblTextContent.setBounds(347, 63, 72, 18);
		panelBrowseTask.add(lblTextContent);
		
		JLabel lblContent = new JLabel("(null)");
		lblContent.setBounds(370, 100, 200, 50);
		panelBrowseTask.add(lblContent);
		
		JLabel lblTextDDL = new JLabel("DDL:");
		lblTextDDL.setBounds(347, 182, 72, 18);
		panelBrowseTask.add(lblTextDDL);
		
		JLabel lblDDL = new JLabel("(null)");
		lblDDL.setBounds(389, 231, 72, 18);
		panelBrowseTask.add(lblDDL);
		
		JLabel lblNumTasks = new JLabel("剩余任务数：0");
		lblNumTasks.setBounds(59, 328, 193, 18);
		panelBrowseTask.add(lblNumTasks);
		
		listTasks.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listTasks.isSelectionEmpty()) {
					lblDDL.setText("(null)");
					lblContent.setText("(null)");
				}else {
					Task selectedTask=listModelTasks.elementAt(listTasks.getSelectedIndex());
					lblContent.setText(selectedTask.getContent());
					if(selectedTask.isDDLEnabled())
						lblDDL.setText(selectedTask.getDDL().toString());
					else
						lblDDL.setText("(null)");
				}
				lblNumTasks.setText("剩余任务数："+listModelTasks.size());
			}
		});
		
		
		
		JButton btnDone = new FairButton("Done");
		btnDone.setBounds(348, 301, 113, 27);
		
		btnDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx=listTasks.getSelectedIndex();
				listModelTasksDone.addElement(listModelTasks.elementAt(idx));
				listModelTasks.remove(idx);
			}
		});
		panelBrowseTask.add(btnDone);
		
		
		JButton btnDelete = new FairButton("Delete");
		btnDelete.setBounds(486, 301, 113, 27);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idx=listTasks.getSelectedIndex();
				listModelTasks.remove(idx);
			}
		});
		panelBrowseTask.add(btnDelete);
	}
	private void initializePanelDoneTask() {
		listTasksDone = new JList();
		listTasksDone.setBounds(64, 59, 231, 238);
		listModelTasksDone = new DefaultListModel<>();
		listTasksDone.setModel(listModelTasksDone);
		panelDoneTask.add(listTasksDone);
		
		JLabel lblNumTasksDone = new JLabel("已完成任务数：0");
		lblNumTasksDone.setBounds(64, 325, 193, 18);
		panelDoneTask.add(lblNumTasksDone);
		
		
		
		JLabel lblTextContent = new JLabel("Content:");
		lblTextContent.setBounds(352, 60, 72, 18);
		panelDoneTask.add(lblTextContent);
		
		JLabel lblContent = new JLabel("(null)");
		lblContent.setBounds(394, 118, 72, 18);
		panelDoneTask.add(lblContent);
		
		JLabel lblNewLabel = new JLabel("DDL:");
		lblNewLabel.setBounds(352, 179, 72, 18);
		panelDoneTask.add(lblNewLabel);
		
		JLabel lblDDL = new JLabel("(null)");
		lblDDL.setBounds(394, 228, 72, 18);
		panelDoneTask.add(lblDDL);
		
		listTasksDone.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listTasksDone.isSelectionEmpty()) {
					lblContent.setText("(null)");
					lblDDL.setText("(null)");
				}else {
					Task selectedTask=listModelTasksDone.elementAt(listTasksDone.getSelectedIndex());
					lblContent.setText(selectedTask.getContent());
					if(selectedTask.isDDLEnabled())
						lblDDL.setText(selectedTask.getDDL().toString());
					else
						lblDDL.setText("(null)");
				}
				lblNumTasksDone.setText("已完成任务数："+listModelTasksDone.size());
			}
		});
	}
	
	static private String _readAll(String filename) throws Exception {
		String encoding = "UTF-8"; 
        File file = new File(filename); 
        Long filelength = file.length(); 
        byte[] filecontent = new byte[filelength.intValue()]; 
        
        FileInputStream in = new FileInputStream(file); 
        in.read(filecontent); 
        in.close(); 
        
        return new String(filecontent, encoding); 
       
	}
	public void load() {
		
		try {
			String strTasks=_readAll("tasks.json");
			java.util.List<Task> tasks=JSON.parseArray(strTasks,Task.class);
			listModelTasks.clear();
			for(Task task:tasks) {
				listModelTasks.addElement(task);
			}
			//这样做是为了触发JList的selection事件，从而让列表下方的“任务数”标志能够正确更新
			if(!tasks.isEmpty())
				listTasks.setSelectedIndex(0);
			
		} catch (FileNotFoundException e) {}//第一次可能会找不到文件，无所谓，重新创建这两文件，不报错了
		  catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "读取任务列表失败！");
			e.printStackTrace();
		}
		
		
		try {
			String strTasksDone=_readAll("tasksDone.json");
			java.util.List<Task> tasksDone=JSON.parseArray(strTasksDone,Task.class);
			listModelTasksDone.clear();
			for(Task taskDone:tasksDone) {
				listModelTasksDone.addElement(taskDone);
			}
			if(!tasksDone.isEmpty())
				listTasksDone.setSelectedIndex(0);
			
		} catch (FileNotFoundException e) {}
		  catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "读取已完成任务列表失败！");
			e.printStackTrace();
		}
	}
	public void save() {
		java.util.List<Task> tasks=new java.util.ArrayList<Task>();
		for(int i=0;i<listModelTasks.getSize();i++) {
			tasks.add(listModelTasks.elementAt(i));
		}
		try {
			FileWriter fileTasks;
			fileTasks = new FileWriter("tasks.json");
			fileTasks.write(JSON.toJSONString(tasks));
			fileTasks.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "保存任务列表失败！");
			e.printStackTrace();
		}
		
		java.util.List<Task> tasksDone=new java.util.ArrayList<Task>();
		for(int i=0;i<listModelTasksDone.getSize();i++) {
			tasksDone.add(listModelTasksDone.elementAt(i));
		}
		try {
			FileWriter fileTasksDone;
			fileTasksDone = new FileWriter("tasksDone.json");
			fileTasksDone.write(JSON.toJSONString(tasksDone));
			fileTasksDone.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "保存已完成任务列表失败！");
			e.printStackTrace();
		}
	}
}
