package game;

import java.util.Random;
import java.util.Scanner;

public class Game2 {
	public static void main(String[] args) {

		System.out.println("=================��ӭ������Ϸ����=====================");
		System.out.println("\n\t\t********************************************");
		System.out.println("\t\t*****              ��ȭ,��ʼ                               ******");
		System.out.println("\t\t***********************************************");
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

			System.out.println("���ȭ:1.���� 2.ʯͷ 3.�� (������Ӧ����):");
			 int show =  input.nextInt();
			 switch(show){
			 case 1:
					System.out.println("���ȭ:����");
					break;
				case 2:
					System.out.println("���ȭ:ʯͷ");
					break;
				case 3:
					System.out.println("���ȭ:��");
					break;
			 }
		
		

				Random r=new Random();
				int show1=r.nextInt(3)+1;
				switch(show){
				case 1:
					System.out.println(rolename+"��ȭ:����");
					break;
				case 2:
					System.out.println(rolename+"��ȭ:ʯͷ");
					break;
				case 3:
					System.out.println(rolename+"��ȭ:��");
					break;
				}
				
			
		  
		}

	}
	//��
/*	private static int  show(Scanner input) {
		System.out.println("���ȭ:1.���� 2.ʯͷ 3.�� (������Ӧ����):");
		 int show =  input.nextInt();
		 switch(show){
		 case 1:
				System.out.println("���ȭ:����");
				break;
			case 2:
				System.out.println("���ȭ:ʯͷ");
				break;
			case 3:
				System.out.println("���ȭ:��");
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
	}*/
	
}
