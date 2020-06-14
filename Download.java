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
		setTitle("下载器");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel name = new JLabel("下载地址：");
		name.setBounds(0, 50, 84, 47);
		setFont(new Font("黑体", Font.BOLD, 20));
		panel.add(name);

		address = new JTextField();
		address.setBounds(98, 55, 297, 36);
		panel.add(address);
		address.setColumns(10);

		JButton down = new JButton("下载");
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

				String message = "已下载，请注意D盘的updown文件夹";
				JOptionPane.showMessageDialog(null, message);
			}

			private void Down(String text) throws Exception {
				// 这是一个下载的私有方法
				// 得到下载地址

				URL url = null;
				url = new URL(text);
				// 建立连接
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				new Thread(new Runnable() {
					@Override
					public void run() {
						FileOutputStream fos = null;
						InputStream inputStream = null;
						try {
							// 使用getInputStream字节输入流
							inputStream = connection.getInputStream();
							String[] arr = text.split("/");
							String filename = arr[arr.length - 1];
							// System.out.println(filename);
							File file = new File("D:" + File.separator + "upload"); // 下载到D盘的uplaod的文件
							// 判断文件是否存在 不存在就创建
							if (!file.exists()) {
								file.mkdirs();
							}
							// 使用字节输出流 将获取的文件存入目标地址
							fos = new FileOutputStream(file + File.separator + "Comptuerdown" + new Random().nextInt(9999) + filename);
							// 传输
							int len = 0;
							byte[] bys = new byte[1024];
							while ((len = inputStream.read(bys)) != -1) {
								fos.write(bys, 0, len);
							}
							// System.out.println(1111);

							// 释放资源
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

				// 断开连接
				connection.disconnect();
			}
		});
		down.setBounds(14, 159, 113, 36);
		setFont(new Font("黑体", Font.BOLD, 20));
		panel.add(down);

		JButton cancel = new JButton("清空");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清空文本框
				address.setText("");
			}
		});
		cancel.setBounds(152, 159, 113, 36);
		setFont(new Font("黑体", Font.BOLD, 20));
		panel.add(cancel);

		JButton exit = new JButton("退出");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 退出程序
				System.exit(0);
			}
		});
		exit.setBounds(283, 159, 113, 36);
		setFont(new Font("黑体", Font.BOLD, 20));
		panel.add(exit);
	}
}
