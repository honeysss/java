package com.chinasofti.meeting.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.chinasofti.meeting.dao.UserDao;
import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.User;

public class UserManage {
	Scanner input = new Scanner(System.in);
	UserDao userDao = new UserDao();
	Login login = new Login();

	
	
	public void userManage(String username) {
		int userId = userDao.selectByName(username).getUid();
		while(true) {
			System.out.println("1�������û�\t2��ɾ���û�\t3���޸��û�\t4�������û�\t5����ѯ�����û�\t6��������һ��");
			System.out.print("��������Ҫ���еĲ���:");
			int chioce = input.nextInt();
			switch(chioce) {
				case 1:
					addUser();
					break;
				case 2:
					deleteUser(userId);
					break;
				case 3:
					updateUser();
					
					break;
				case 4:
					selectUser();
					break;
				case 5:
					selectAllUser();
					break;
				case 6:
					System.out.println();
					return;
				default:
					System.out.println("û�и�ѡ�������ѡ��");
					break;
			}
		}
	}


	
	
//	����û�
	public void addUser() {
		System.out.print("�������û�������:");
		String newName = input.next();
		
//		�ж��Ƿ������ͬ���û�
		if (userDao.ifExistUser(newName)) {
			System.out.println("���û��Ѵ���,������һ������û���");
			addUser();
			return;
		} else {
			int newId = Init.userList.get(Init.userList.size() - 1).getUid() + 1;
			System.out.print("�������û�������:");
			String newPwd = input.next();
			
			System.out.println("1������Ա\t0����ͨ�û�");
			System.out.print("�������û������:");
			int newRole = input.nextInt();
			
			User newUser = new User(newId, newName, newPwd, newRole);
			System.out.println("����û�" + newName + "�ɹ�\n");
			userDao.add(newUser);
		}
	}
	
//	�޸��û�
	public void updateUser() {

		selectAllUser();
		System.out.println("1��yes\t0��no");
		System.out.print("�����޸��û��������:");
		String chioce = input.next();
		
		System.out.print("��������Ҫ�޸ĵ��û���id:");
		int updateId = input.nextInt();
//			�ж��û��Ƿ����
		if (userDao.selectById(updateId) != null) {
			

			System.out.print("�������޸ĺ���û�������:");
			String updateNewName = input.next();

			
			System.out.print("�������޸ĺ���û�������:");
			String updateNewPwd = input.next();
			System.out.print("���ٴ������޸ĺ���û�������:");
			String updateNewPwdAgain = input.next();
			
			if (chioce.equals("yes")) {
				if (updateNewPwd.equals(updateNewPwdAgain)) {
					System.out.print("�������޸ĺ���û������:");
					int updateNewRole = input.nextInt();
					
					User updateNewUser = new User(updateId, updateNewName, updateNewPwd, updateNewRole);
					
					userDao.update(updateId, updateNewUser);
				} else {
					System.out.println("���벻��ȷ,����������");
					updateUser();
				}
			} else {
				if (updateNewPwd.equals(updateNewPwdAgain)) {
					
					int updateNewRole = userDao.selectById(updateId).getRole();
					
					User updateNewUser = new User(updateId, updateNewName, updateNewPwd, updateNewRole);
					
					userDao.update(updateId, updateNewUser);
				} else {
					System.out.println("���벻��ȷ,����������");
					updateUser();
				}
			}
			
			
			
			
		} else {
			System.out.print("���û�������\n");
		}
	}
	
	
//	ɾ���û�
	public void deleteUser(int userId) {
		selectAllUser();
		
		System.out.print("��������Ҫɾ�����û���id:");
		int deleteId = input.nextInt();
		

//			�����ǰֻ��һ���û�  ����ɾ�������Լ� ��ʾ ����Լ�ɾ����
//		ͨ���û������ҵ��û�id ������ȵĻ�
		
		if (Init.userList.size() == 1 || userId == deleteId) {
			userDao.delete(deleteId);
			System.out.println("����Լ�ɾ���ˣ��ص�����ҳ");
			login.login();
		}
		
		
		if (!userDao.delete(deleteId)) {
			System.out.println("�û�id������");
		}
		
	}
	
//	�鿴�û� ����
	public void selectUser() {
		System.out.println("1��ͨ���û�����ѯ\t2��ͨ���û�id��ѯ");
		System.out.print("�����������ѯ�ķ���:");
		int selectMethod = input.nextInt();
		switch(selectMethod) {
			case 1:
				System.out.print("�������û���:");
				String selectUsername = input.next();
				if (userDao.selectByName(selectUsername) != null) {
					System.out.println(userDao.selectByName(selectUsername));
				} else {
					System.out.println("�����ڸ��û�");
				}
				break;
			case 2:
				System.out.print("�������û�id:");
				int selectId = input.nextInt();
				if (userDao.selectById(selectId) != null) {
					System.out.println(userDao.selectById(selectId));
				} else {
					System.out.println("�����ڸ��û�");
				}
				break;
			default:
				break;
		}
	}
	
//	��ѯ�����û�
	public void selectAllUser() {
		ArrayList<User> users = new ArrayList<User>();
		users = userDao.selectAll();
		if (users.size() != 0) {
			for (int i = 0; i < users.size(); i++) {
				System.out.println(users.get(i));
			}
		} else {
			System.out.println("��û���û���Ϣ����ȥ����û���");
		}
	}


}
