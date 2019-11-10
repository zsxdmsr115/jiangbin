package game;

import java.util.Random;
import java.util.Scanner;

public class Game3 {
	public static void main(String[] args) {

		System.out.println("=================��ӭ������Ϸ����=====================");
		System.out.println("\n\t\t********************************************");
		System.out.println("\t\t*****              ��ȭ,��ʼ                               ******");
		System.out.println("\t\t***********************************************");
		String username = inputName();
		System.out.println("\n\n��ȭ����:1.���� 2. ʯͷ  3. ��");
		System.out.println("��ѡ�����Ķ���(1.ϲ����   2.��̫��   3.�󲮲�)");
		Scanner input = new Scanner(System.in);

		String rolename = null;
		boolean flag = true;
		while (flag) {
			int role = input.nextInt();
			switch (role) {
			case 1:
				rolename = "ϲ����";
				flag = false;
				break;
			case 2:
				rolename = "��̫��";
				flag = false;
				break;
			case 3:
				rolename = "�󲮲�";
				flag = false;
				break;

			default:
				System.out.println("����������������ѡ��");
				break;
			}
		}
		System.out.println("\nҪ��ʼ��?(y/n)");
		String con = input.next();
		 int perFist;
		 int computerFist;
		while (con.equals("y")) {
		  perFist = show(username,input);
		  computerFist=computerShow(rolename);
		  if(perFist==computerFist){
			 System.out.println("���:�;�,��˥���ٺ�,�����ư� !\n"); // ƽ��
			 //1 ,3  2 1 3 2
		  }else if((perFist==1 && computerFist ==3)||
				   (perFist==2 && computerFist==3)||
				   (perFist==3 && computerFist==2)){
			  System.out.println("�������ϲ "+username+"Ӯ��");
			  
		  }else{
			  System.out.println("�����"+username+"���ˣ��汿");
		  }
		}

	}
	public  static String inputName(){
		     Scanner sc = new Scanner(System.in);
		     System.out.println("�������û���");
		    return   sc.next();
	}
	//��
	private static int  show(String username,Scanner input) {
		System.out.println("���ȭ:1.���� 2.ʯͷ 3.�� (������Ӧ����):");
		 int show =  input.nextInt();
		 switch(show){
		 case 1:
				System.out.println(username+"��ȭ:����");
				break;
			case 2:
				System.out.println(username+"��ȭ:ʯͷ");
				break;
			case 3:
				System.out.println(username+"��ȭ:��");
				break;
		 }
		 return show;
	}
	
  //��
	private static int computerShow(String name){
		Random r=new Random();
		int show=r.nextInt(3)+1;
		switch(show){
		case 1:
			System.out.println(name+"��ȭ:����");
			break;
		case 2:
			System.out.println(name+"��ȭ:ʯͷ");
			break;
		case 3:
			System.out.println(name+"��ȭ:��");
			break;
		}
		return show;
	}
	
}
