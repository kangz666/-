package TimeControl;

import java.util.Calendar;

import javax.swing.JTextArea;

public class GetTime implements Runnable {
	private Time t;   //设置判断和作为一把对象锁
	private JTextArea textArea;  //文本域显示当前时间
	
	public GetTime(Time t,JTextArea textArea) {
		super();
		this.t = t;
		this.textArea=textArea;
	}
	public void run() {	
		//使时间都拼接成字符串的形式
		StringBuffer sb = new StringBuffer();
		//String s = "sss";
		String str = null;
			synchronized(t) {
				while(true) {
					//获取当前时间
					Calendar c = Calendar.getInstance();  //可以对每个时间域单独修改
					int year = c.get(Calendar.YEAR);
					int month = c.get(Calendar.MONTH)+1;
					int date = c.get(Calendar.DATE);
					int hour = c.get(Calendar.HOUR_OF_DAY);
					int minute = c.get(Calendar.MINUTE);
					int second = c.get(Calendar.SECOND);
					//如果没有整点 就继续显示
					if(!t.flag) {
					//System.out.println(Thread.currentThread().getName()+year + "年" + month + "月" + date + "日" +hour + "时" +minute + "分" + second+"秒");
						str = sb.append(year).append("年").append(month).append("月").append(date).append("日").append(hour).append("时").append(minute).append("分").append(second).append("秒").toString();
						textArea.setText(str);
											}
					else
					{	//出现整点 修改Time对象的flag  使SetTime 出现报时
						//System.out.println(Thread.currentThread().getName()+year + "年" + month + "月" + date + "日" +hour + "时" +minute + "分" + second+"秒");
						//	System.out.println("整点报时！");
						str = sb.append(year).append("年").append(month).append("月").append(date).append("日").append(hour).append("时").append(minute).append("分").append(second).append("秒").toString();
						textArea.setText(str);
						t.flag=false;
					}
					try {
						Thread.sleep(1000);
						//清楚sb缓冲区的字符串 
						sb.delete(0, str.length());

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
		
	}

	
}
