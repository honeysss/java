package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.User;

public class Login {
	UserDao userDao = new UserDao();
	Scanner input = new Scanner(System.in);
	public void login() {
		System.out.println("1����½\t2��ע��\t3���˳�");
		System.out.print("��ѡ����Ҫ���еĲ���:");
		int chioce3 = 0;
		if (input.hasNextInt()) {
			chioce3 = input.nextInt();
			choose(chioce3);
		} else {
			System.out.println("����������������������");
		}
	}
	
//	ѡ��
	public void choose(int chioce3) {
		while(true) {
			switch(chioce3) {
				case 1:
					register();
					break;
				case 2:
					sign();
					
					break;
				case 3:
					System.out.println("�ټ�����ӭ�´ι���");
					System.exit(0);
					return;
				default:
					System.out.println("û�и�ѡ�������ѡ��");
					break;
			}
		}
	}
	
//	��½
	public void register() {
		while(true) {
			System.out.print("����������û���:");
			String username = input.next();

			System.out.print("�������������:");
			String pwd = input.next();
			
//			������ڸ��û�����������ȷ
			if (userDao.selectByName(username) != null) {
				if (userDao.selectByName(username).getPwd().equals(pwd)) {
//					����Ա
					if (userDao.checkRole(username)) {
						System.out.println("����Ա" + username +"��½�ɹ�!\n");
						AdminView adminView = new AdminView();
						adminView.adminView(username);
//						��ͨ�û�
					} else {
						System.out.println("��ͨ�û�" + username +"��½�ɹ�!\n");
						UserView userView = new UserView();
						userView.userView(username);
					}
					
				} else {
					System.out.println("���벻��ȷ");
				}
			} else {
				System.out.println("���û�������");
			}
		}
	}

//	ע��
	public void sign() {
		System.out.println();
		while (true) {
			System.out.print("�������û���:");
			String signName = input.next();

//			�ж��Ƿ������ͬ���û�
			if (userDao.ifExistUser(signName)) {
				System.out.println("���û��Ѵ���,������һ������û���");
			} else {
				System.out.print("����������:");
				String signPwd = input.next();
				System.out.print("���ٴ�������������:");
				String signPwdAgain = input.next();


				int signId = Init.userList.get(Init.userList.size() - 1).getUid() + 1;
				
				if (signPwd.equals(signPwdAgain)) {
					userDao.add(new User(signId, signName, signPwd, 0));
					System.out.println("����û�" + signName + "�ɹ�\n");
					login();
				} else {
					System.out.println("�������벻ͬ, ����������");
				}
			}
			
		}
		
	}
}
