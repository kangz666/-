package Down;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Download extends JFrame {

	private JPanel contentPane;
	private JTextField address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Download frame = new Download();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Download() {
		setTitle("������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel name = new JLabel("���ص�ַ��");
		name.setBounds(0, 50, 84, 47);
		setFont(new Font("����", Font.BOLD, 20));
		panel.add(name);

		address = new JTextField();
		address.setBounds(98, 55, 297, 36);
		panel.add(address);
		address.setColumns(10);

		JButton down = new JButton("����");
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = address.getText();
				// System.out.println(text);
				try {
					Down(text);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String message = "�����أ���ע��D�̵�updown�ļ���";
				JOptionPane.showMessageDialog(null, message);
			}

			private void Down(String text) throws Exception {
				// ����һ�����ص�˽�з���
				// �õ����ص�ַ

				URL url = null;
				url = new URL(text);
				// ��������
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				new Thread(new Runnable() {
					@Override
					public void run() {
						FileOutputStream fos = null;
						InputStream inputStream = null;
						try {
							// ʹ��getInputStream�ֽ�������
							inputStream = connection.getInputStream();
							String[] arr = text.split("/");
							String filename = arr[arr.length - 1];
							// System.out.println(filename);
							File file = new File("D:" + File.separator + "upload"); // ���ص�D�̵�uplaod���ļ�
							// �ж��ļ��Ƿ���� �����ھʹ���
							if (!file.exists()) {
								file.mkdirs();
							}
							// ʹ���ֽ������ ����ȡ���ļ�����Ŀ���ַ
							fos = new FileOutputStream(file + File.separator + "Comptuerdown" + new Random().nextInt(9999) + filename);
							// ����
							int len = 0;
							byte[] bys = new byte[1024];
							while ((len = inputStream.read(bys)) != -1) {
								fos.write(bys, 0, len);
							}
							// System.out.println(1111);

							// �ͷ���Դ
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (fos != null) {
								try {
									fos.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} finally {
									if (inputStream != null) {
										try {
											inputStream.close();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}

						}

					}
				}).start();

				// �Ͽ�����
				connection.disconnect();
			}
		});
		down.setBounds(14, 159, 113, 36);
		setFont(new Font("����", Font.BOLD, 20));
		panel.add(down);

		JButton cancel = new JButton("���");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ı���
				address.setText("");
			}
		});
		cancel.setBounds(152, 159, 113, 36);
		setFont(new Font("����", Font.BOLD, 20));
		panel.add(cancel);

		JButton exit = new JButton("�˳�");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˳�����
				System.exit(0);
			}
		});
		exit.setBounds(283, 159, 113, 36);
		setFont(new Font("����", Font.BOLD, 20));
		panel.add(exit);
	}
}
