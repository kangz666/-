package TimeControl;

import java.util.Calendar;

import javax.swing.JTextArea;

public class GetTime implements Runnable {
	private Time t;   //�����жϺ���Ϊһ�Ѷ�����
	private JTextArea textArea;  //�ı�����ʾ��ǰʱ��
	
	public GetTime(Time t,JTextArea textArea) {
		super();
		this.t = t;
		this.textArea=textArea;
	}
	public void run() {	
		//ʹʱ�䶼ƴ�ӳ��ַ�������ʽ
		StringBuffer sb = new StringBuffer();
		//String s = "sss";
		String str = null;
			synchronized(t) {
				while(true) {
					//��ȡ��ǰʱ��
					Calendar c = Calendar.getInstance();  //���Զ�ÿ��ʱ���򵥶��޸�
					int year = c.get(Calendar.YEAR);
					int month = c.get(Calendar.MONTH)+1;
					int date = c.get(Calendar.DATE);
					int hour = c.get(Calendar.HOUR_OF_DAY);
					int minute = c.get(Calendar.MINUTE);
					int second = c.get(Calendar.SECOND);
					//���û������ �ͼ�����ʾ
					if(!t.flag) {
					//System.out.println(Thread.currentThread().getName()+year + "��" + month + "��" + date + "��" +hour + "ʱ" +minute + "��" + second+"��");
						str = sb.append(year).append("��").append(month).append("��").append(date).append("��").append(hour).append("ʱ").append(minute).append("��").append(second).append("��").toString();
						textArea.setText(str);
											}
					else
					{	//�������� �޸�Time�����flag  ʹSetTime ���ֱ�ʱ
						//System.out.println(Thread.currentThread().getName()+year + "��" + month + "��" + date + "��" +hour + "ʱ" +minute + "��" + second+"��");
						//	System.out.println("���㱨ʱ��");
						str = sb.append(year).append("��").append(month).append("��").append(date).append("��").append(hour).append("ʱ").append(minute).append("��").append(second).append("��").toString();
						textArea.setText(str);
						t.flag=false;
					}
					try {
						Thread.sleep(1000);
						//���sb���������ַ��� 
						sb.delete(0, str.length());

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}
		
	}

	
}
