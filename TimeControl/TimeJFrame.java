package TimeControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TimeJFrame extends JFrame {

	private JPanel contentPane;
	private Time t;
	private GetTime g;
	private SetTime ss;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeJFrame frame = new TimeJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimeJFrame() {
		//设置标题
		setTitle("整点报时");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("当前的时间是：");
		label.setBounds(14, 13, 80, 34);
		contentPane.add(label);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(14, 65, 428, 45);
		contentPane.add(textArea);
		//设置文本域的属性 字体 大小
		textArea.setFont(new Font("黑体",Font.BOLD,32));
		
		t=new Time();
		
		//调用GetTime类
		g=new GetTime(t,textArea);
		//开始线程
		new Thread(g).start();
		
		JLabel setTime = new JLabel();
		setTime.setBounds(22, 164, 420, 59);
		contentPane.add(setTime);
		//设置标签的属性 字体 大小
		setTime.setFont(new Font("黑体",Font.BOLD,40));
		//setTime.setForeground(Color.RED);
		//显示空格
		setTime.setText(" ");
		//调用SetTime类 并开始线程
		ss=new SetTime(t,setTime);
		new Thread(ss).start();
		
	}
}
