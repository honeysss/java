package com.chinasofti.meeting.dao;

import java.util.ArrayList;

import com.chinasofti.meeting.util.Init;
import com.chinasofti.meeting.vo.User;

public class UserDao {
	//	��
	public void add(User user) {
		Init.userList.add(user);
	}
	
//	ɾ ͬʱ�ж�id�Ƿ����
	public Boolean delete(int id) {
		Boolean flag = false;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(id == Init.userList.get(i).getUid()) {
				flag = true;
				String name = Init.userList.get(i).getUname();
				Init.userList.remove(Init.userList.get(i));
				System.out.println("ɾ���û�" + name + "�ɹ�");
				break;
			}
		}
		return flag;
	}
	
//	��
	public void update(int id, User user) {
		for (int i = 0; i < Init.userList.size(); i++) {
			if(id == Init.userList.get(i).getUid()) {
				Init.userList.set(i, user);
				System.out.println("�޸��û�" + user.getUname() + "�ɹ�\n");
				break;
			}
		}
	}

	
//	��
//	ȫ����ѯ
	public ArrayList<User> selectAll() {
		return Init.userList;
	}
	
//	ͨ���û�id��ѯ
	public User selectById(int id) {
		User user = null;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(id == Init.userList.get(i).getUid()) {
				user = Init.userList.get(i);
			}
		}
		return user;
	}
	
//	ͨ���û�����ѯ
	public User selectByName(String username) {
		User user = null;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(username.equals(Init.userList.get(i).getUname())) {
				user = Init.userList.get(i);
			}
		}
		return user;
	}
	

//	����û������ true�ǹ���Ա false����ͨ�û�
	public Boolean checkRole(String username) {
		Boolean exist = false;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(username.equals(Init.userList.get(i).getUname())) {
				if (Init.userList.get(i).getUid() == 1) {
					exist = true;
				}
			}
		}
		return exist;
	}

	
//	�ж����ӻ���ע����û��Ƿ��Ѵ���(�Ƿ������ͬ���û���)
	public Boolean ifExistUser(String username) {
		Boolean exist = false;
		for (int i = 0; i < Init.userList.size(); i++) {
			if(username.equals(Init.userList.get(i).getUname())) {
				exist = true;
			}
		}
		return exist;
	}
	
	

}
