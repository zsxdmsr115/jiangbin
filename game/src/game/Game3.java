package game;

import java.util.Random;
import java.util.Scanner;

public class Game3 {
	public static void main(String[] args) {

		System.out.println("=================欢迎进入游戏世界=====================");
		System.out.println("\n\t\t********************************************");
		System.out.println("\t\t*****              猜拳,开始                               ******");
		System.out.println("\t\t***********************************************");
		String username = inputName();
		System.out.println("\n\n出拳规则:1.剪刀 2. 石头  3. 布");
		System.out.println("请选择您的对手(1.喜羊羊   2.灰太狼   3.象伯伯)");
		Scanner input = new Scanner(System.in);

		String rolename = null;
		boolean flag = true;
		while (flag) {
			int role = input.nextInt();
			switch (role) {
			case 1:
				rolename = "喜羊羊";
				flag = false;
				break;
			case 2:
				rolename = "灰太狼";
				flag = false;
				break;
			case 3:
				rolename = "象伯伯";
				flag = false;
				break;

			default:
				System.out.println("您输入有误请重新选择");
				break;
			}
		}
		System.out.println("\n要开始吗?(y/n)");
		String con = input.next();
		 int perFist;
		 int computerFist;
		while (con.equals("y")) {
		  perFist = show(username,input);
		  computerFist=computerShow(rolename);
		  if(perFist==computerFist){
			 System.out.println("结果:和局,真衰！嘿嘿,等着瞧吧 !\n"); // 平局
			 //1 ,3  2 1 3 2
		  }else if((perFist==1 && computerFist ==3)||
				   (perFist==2 && computerFist==3)||
				   (perFist==3 && computerFist==2)){
			  System.out.println("结果：恭喜 "+username+"赢了");
			  
		  }else{
			  System.out.println("结果："+username+"输了，真笨");
		  }
		}

	}
	public  static String inputName(){
		     Scanner sc = new Scanner(System.in);
		     System.out.println("请输入用户名");
		    return   sc.next();
	}
	//人
	private static int  show(String username,Scanner input) {
		System.out.println("请出拳:1.剪刀 2.石头 3.布 (输入相应数字):");
		 int show =  input.nextInt();
		 switch(show){
		 case 1:
				System.out.println(username+"出拳:剪刀");
				break;
			case 2:
				System.out.println(username+"出拳:石头");
				break;
			case 3:
				System.out.println(username+"出拳:布");
				break;
		 }
		 return show;
	}
	
  //机
	private static int computerShow(String name){
		Random r=new Random();
		int show=r.nextInt(3)+1;
		switch(show){
		case 1:
			System.out.println(name+"出拳:剪刀");
			break;
		case 2:
			System.out.println(name+"出拳:石头");
			break;
		case 3:
			System.out.println(name+"出拳:布");
			break;
		}
		return show;
	}
	
}
