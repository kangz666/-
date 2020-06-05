package Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/*
 * 斗地主的发牌和洗牌
 * 
 * 分析：
 * 		A:创建一个HashMap集合
 * 		B:创建一个ArrayList集合
 * 		C:创建花色数组和点数数组
 * 		D:从0开始往HashMap里存储编号，并存储相对应的牌
 * 			同时往ArrayList里存储编号
 * 		E:看牌（遍历TreeSet集合，获取编号，到HashMap集合找到相对应 的牌）
 */
public class Ferak {

	public static void main(String[] args) {
		//创建HashMap集合
		HashMap<Integer,String> hm =new HashMap<Integer,String>();
		ArrayList<Integer> array =new ArrayList<Integer>();
		//花色
		String[] colors = {"♠","♥","♣","♦"};
		String[] numbers= {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
		//装牌
		int index =0;
		for(String number:numbers) {
			for(String color:colors) {
				String poker =color.concat(number);
				hm.put(index,poker);
				array.add(index);
				index++;
			}
		}
		//添加大小王
		hm.put(index,"小王");
		array.add(index);
		index++;
		hm.put(index,"大王");
		array.add(index);
		
		//洗牌(洗的是编号)
		Collections.shuffle(array);
		
		//发牌
		TreeSet<Integer> w =new TreeSet<Integer>();
		TreeSet<Integer> n =new TreeSet<Integer>();
		TreeSet<Integer> t =new TreeSet<Integer>();
		TreeSet<Integer> dipai =new TreeSet<Integer>();
		//随机发牌
		for(int x=0;x<array.size();x++) {
		   if(x>=array.size()-3) {
			   dipai.add(array.get(x));
		   }
			else if(x%3==0) {
				w.add(array.get(x));
			}
			else if(x%3==1) {
				n.add(array.get(x));
			}
			else if(x%3==2) {
				t.add(array.get(x));
			}
			
			
		}
		//遍历
		look("我",w,hm);
		look("你",n,hm);
		look("他",t,hm);
		look("底牌",dipai,hm);
	}
	
	public static void look(String name,TreeSet<Integer> ts,HashMap<Integer,String> hm) {
		System.out.print(name+"的牌是：");
		for(Integer key:ts) {
			//通过TreeSet中的号码值去找 HashMap中的 poker
			String value =hm.get(key);
			System.out.print(value+" ");
		}
		System.out.println();
	}

}
