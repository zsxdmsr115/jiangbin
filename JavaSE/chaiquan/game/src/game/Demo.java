package game;

import java.util.Scanner;

public class Demo {
  public static void main(String[] args) {
	System.out.println("\t\t\t===================��ӭ������Ϸ����=====================");
	System.out.println("\t\t\t\t******************************");
	System.out.println("\t\t\t\t****** ��ȭ����ʼ          ***********");
	System.out.println("\t\t\t\t*****************************");
	System.out.println("�����������");
	Scanner sc = new Scanner(System.in);
	String inputName = sc.next();
	System.out.println("��ȭ����1.���� 2.ʯͷ 3.��");
	System.out.println("��ѡ����Ķ���(1.ϲ����2.��̫��  3.�󲮲�)");
	String rolename = sc.nextLine();
	System.out.println("��Ķ���"+rolename);
  }
}
