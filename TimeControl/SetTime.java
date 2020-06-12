package TimeControl;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.JLabel;

public class SetTime implements Runnable {
	private Time t;
	private JLabel setTime;
	public SetTime(Time t,JLabel setTime) {
		super();
		this.t = t;
		this.setTime=setTime;
	}

	@Override
	public void run() {	
		while (true) {
		synchronized (new Object()) {
					//ÿ�ζ������¶��� ������ȥɾȥһֱƴ�ӵ����
					//ʹʱ�䶼ƴ�ӳ��ַ�������ʽ
					StringBuffer sb = new StringBuffer();
					String str = null;
					//��ȡ��ǰ��ʱ��
					Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
					int year = c.get(Calendar.YEAR);
					int month = c.get(Calendar.MONTH);
					int date = c.get(Calendar.DATE);
					int hour = c.get(Calendar.HOUR_OF_DAY);
					int minute = c.get(Calendar.MINUTE);
					int second = c.get(Calendar.SECOND);
					//setTime.setForeground(Color.BLACK);
					//���û������Ͳ���ʾ
					setTime.setText(" ");
					//�֣��붼����0 ʱ��������
					if(second==0) {
						t.flag=true;
						str = sb.append(hour).append("ʱ").toString();
						//��ʾʱ�� ����ʱ
						setTime.setText("���㱨ʱ"+str+"ding~~~");
						//�����Ϊ��ɫ
						setTime.setForeground(Color.RED);
						
					}
					
					try {
						Thread.sleep(1000);
						//���һ��
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}

	}


