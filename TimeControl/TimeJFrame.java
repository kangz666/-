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
		//���ñ���
		setTitle("���㱨ʱ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("��ǰ��ʱ���ǣ�");
		label.setBounds(14, 13, 80, 34);
		contentPane.add(label);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(14, 65, 428, 45);
		contentPane.add(textArea);
		//�����ı�������� ���� ��С
		textArea.setFont(new Font("����",Font.BOLD,32));
		
		t=new Time();
		
		//����GetTime��
		g=new GetTime(t,textArea);
		//��ʼ�߳�
		new Thread(g).start();
		
		JLabel setTime = new JLabel();
		setTime.setBounds(22, 164, 420, 59);
		contentPane.add(setTime);
		//���ñ�ǩ������ ���� ��С
		setTime.setFont(new Font("����",Font.BOLD,40));
		//setTime.setForeground(Color.RED);
		//��ʾ�ո�
		setTime.setText(" ");
		//����SetTime�� ����ʼ�߳�
		ss=new SetTime(t,setTime);
		new Thread(ss).start();
		
	}
}
