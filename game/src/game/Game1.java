package game;

import java.util.Scanner;

public class Game1 {
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
		while (flag) {  //����bug
			int role = input.nextInt();
			switch (role) {
			case 1:
				rolename = "ϲ����";
				flag=false;
				break;
			case 2:
				rolename = "��̫��";
				flag=false;
				break;
			case 3:
				rolename = "�󲮲�";
				flag=false;
				break;

			default:
				System.out.println("����������������ѡ��");
				break;
			}
		}
		System.out.println("��ѡ��Ķ�����"+rolename);
	}
}
