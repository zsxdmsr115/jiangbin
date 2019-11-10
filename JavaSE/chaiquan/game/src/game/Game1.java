package game;

import java.util.Scanner;

public class Game1 {
	public static void main(String[] args) {
		System.out.println("=================欢迎进入游戏世界=====================");
		System.out.println("\n\t\t********************************************");
		System.out.println("\t\t*****              猜拳,开始                               ******");
		System.out.println("\t\t***********************************************");
		System.out.println("\n\n出拳规则:1.剪刀 2. 石头  3. 布");
		System.out.println("请选择您的对手(1.喜羊羊   2.灰太狼   3.象伯伯)");
		Scanner input = new Scanner(System.in);
		
		String rolename = null;
		boolean flag = true;
		while (flag) {  //做个bug
			int role = input.nextInt();
			switch (role) {
			case 1:
				rolename = "喜羊羊";
				flag=false;
				break;
			case 2:
				rolename = "灰太狼";
				flag=false;
				break;
			case 3:
				rolename = "象伯伯";
				flag=false;
				break;

			default:
				System.out.println("您输入有误请重新选择");
				break;
			}
		}
		System.out.println("你选择的对手是"+rolename);
	}
}
