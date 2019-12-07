package game;

import java.util.Random;
import java.util.Scanner;

public class Game4 {
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
		int count = 0;
		int perScore = 0; // ��ҵ÷�
		int computerScore = 0;// ���Ե÷�
		while (con.equals("y")) {
			perFist = show(username, input);
			computerFist = computerShow(rolename);
			if (perFist == computerFist) {
				System.out.println("���:�;�,��˥���ٺ�,�����ư� !\n"); // ƽ��
				// 1 ,3 2 1 3 2
			} else if ((perFist == 1 && computerFist == 3) || (perFist == 2 && computerFist == 3)
					|| (perFist == 3 && computerFist == 2)) {
				System.out.println("�������ϲ " + username + "Ӯ��");
				perScore++;

			} else {
				System.out.println("�����" + username + "���ˣ��汿");
				computerScore++;
			}
			count++;
			System.out.println("\n�Ƿ�ʼ��һ�֣�y/n��");
			con = input.next();

		}
		System.out.println("======");
		System.out.println(username + "vs" + rolename);
		System.out.println("��ս����" + count);
		if (perScore == computerScore) {
			System.out.println("���:ƽ����ɫ���´κ���һ������");
		}else if(perScore>computerScore){
			System.out.println("���"+username+"���һ���ϲ��ϲ");
		}else{
			System.out.println("�����"+username+"̫���ˣ��´μ��Ͱ�"+rolename+"��ʤ��");
		}

	}

	public static String inputName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("�������û���");
		return sc.next();
	}

	// ��
	private static int show(String username, Scanner input) {
		System.out.println("���ȭ:1.���� 2.ʯͷ 3.�� (������Ӧ����):");
		int show = input.nextInt();
		switch (show) {
		case 1:
			System.out.println(username + "��ȭ:����");
			break;
		case 2:
			System.out.println(username + "��ȭ:ʯͷ");
			break;
		case 3:
			System.out.println(username + "��ȭ:��");
			break;
		default:
			
			break;
		}
		return show;
	}

	// ��
	private static int computerShow(String name) {
		Random r = new Random();
		int show = r.nextInt(3) + 1;
		switch (show) {
		case 1:
			System.out.println(name + "��ȭ:����");
			break;
		case 2:
			System.out.println(name + "��ȭ:ʯͷ");
			break;
		case 3:
			System.out.println(name + "��ȭ:��");
			break;
		}
		return show;
	}

}
