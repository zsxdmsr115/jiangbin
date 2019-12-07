package game;

import java.util.Scanner;

public class Demo {
  public static void main(String[] args) {
	System.out.println("\t\t\t===================欢迎进入游戏世界=====================");
	System.out.println("\t\t\t\t******************************");
	System.out.println("\t\t\t\t****** 猜拳，开始          ***********");
	System.out.println("\t\t\t\t*****************************");
	System.out.println("输入你的名字");
	Scanner sc = new Scanner(System.in);
	String inputName = sc.next();
	System.out.println("出拳规则：1.剪刀 2.石头 3.布");
	System.out.println("请选择你的对手(1.喜洋洋。2.灰太狼  3.象伯伯)");
	String rolename = sc.nextLine();
	System.out.println("你的对手"+rolename);
  }
}
