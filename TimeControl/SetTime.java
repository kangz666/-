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
					//每次都是重新定义 可以免去删去一直拼接的情况
					//使时间都拼接成字符串的形式
					StringBuffer sb = new StringBuffer();
					String str = null;
					//获取当前的时间
					Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
					int year = c.get(Calendar.YEAR);
					int month = c.get(Calendar.MONTH);
					int date = c.get(Calendar.DATE);
					int hour = c.get(Calendar.HOUR_OF_DAY);
					int minute = c.get(Calendar.MINUTE);
					int second = c.get(Calendar.SECOND);
					//setTime.setForeground(Color.BLACK);
					//如果没有整点就不显示
					setTime.setText(" ");
					//分，秒都等于0 时就是整点
					if(second==0) {
						t.flag=true;
						str = sb.append(hour).append("时").toString();
						//显示时间 并报时
						setTime.setText("整点报时"+str+"ding~~~");
						//字体改为红色
						setTime.setForeground(Color.RED);
						
					}
					
					try {
						Thread.sleep(1000);
						//间隔一秒
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}

	}


