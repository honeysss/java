package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import model.Food;
import model.Order;
import model.Userr;
import dao.FoodDao;
import dao.OrderDao;
import dao.UserrDao;

public class UserrInter {
	UserrDao userrDao=new UserrDao();
	Userr userr=new Userr();
	OrderDao orderDao=new OrderDao();
	Order order=new Order();
	FoodDao foodDao=new FoodDao();
	Food food=new Food();
	Scanner input=new Scanner(System.in);
	MainInter mainInter = new MainInter();

	//�û� 
	public void userr(String username,String psw) throws Exception {
		boolean circle=true;
		do {
			System.out.println("\t\t\t----�û�����----");
			System.out.println("  \t\t\t1���鿴��Ʒ��Ϣ");
			System.out.println("  \t\t\t2����Ҫ����");
			System.out.println("  \t\t\t3���鿴����");
			System.out.println("  \t\t\t4��ǩ�ն���");
			System.out.println("  \t\t\t5��ȡ������");
			System.out.println("  \t\t\t6����Ҫ����");
			System.out.println("  \t\t\t7���޸�����");
			System.out.println("  \t\t\t8���޸��ֻ���");
			System.out.println("  \t\t\t9���޸ĵ�ַ");
			System.out.println("  \t\t\t10��������һ��");
			System.out.print("  \t\t\t��ѡ����Ҫ���еĲ���:");
			int choice3=input.nextInt();
			switch(choice3) {
				case 1:	
					//�˿Ͳ鿴��Ʒ��Ϣ
					ArrayList<Food> alist = new ArrayList<Food>();
					alist = foodDao.selectFood();
					for(int i=0;i<alist.size();i++){
						System.out.println("\t\t\t"+alist.get(i));
					}
					break;
				case 2:
					eat(username,psw);
					break;
				case 3:
					//�˿Ͳ鿴����
					userLookOrder(username);
					break;
				case 4:
					//ǩ�ն���
					signOrder(username,psw);
					break;
				case 5:
					//ȡ������
					cancleOrder(username,psw);
					break;
				case 6:
					//����
					upFood(username,psw);
					break;
				case 7:
					//�û��޸�����
					userUpdatePsw(username,psw);
					circle = false;
				case 8:
					//�û��޸��ֻ���
					userUpdateTel(username);
					break;
				case 9:
					//�޸ĵ�ַ
					updateAddr(username);
					break;
				case 10:
					mainInter.start();
					break;
				default:
					System.out.println("\t\t\tû�д�ѡ�������ѡ��:");
					break;
			}
		}while(circle);
		
	}

	//�û�����
	private void eat(String username,String psw) throws Exception{
		orderDao.creatOrder(username);
		int orderId = orderDao.findId(username);
		boolean circle=true;
		float totalPrice = 0;
		String seTime = null;
		String addr = null;
		System.out.println("\t\t\t��Ʒ��Ϣ����:");
		ArrayList<Food> alist1 = foodDao.selectFood();
		for(int i=0;i<alist1.size();i++){
			System.out.println("\t\t\t"+alist1.get(i));
		}
		while(circle){
			System.out.print("\t\t\t�������Ʒ���:");
			int foodId = input.nextInt();
			Food food = foodDao.idName(foodId);
			String foodName = food.getFoodName();
			float price = food.getPrice();
			int state = foodDao.idState(foodId);
			if(state == 1){
				System.out.print("\t\t\t���������:");
				int orderNum = input.nextInt();
				totalPrice = totalPrice+price*orderNum;
				int num = foodDao.foodIdFind(foodName, orderId);
				if(num==1) {	//�������ͬ�Ķ�����������ţ������˶���ͬ��
					boolean circle3 = true;
					orderDao.orderNumAdd(orderNum, foodName, orderId);
					while(circle3) {
						System.out.print("\t\t\t�Ƿ�Ҫ�������� yes/no:");
						String choice = input.next();
						if(choice.equals("yes")){
							circle3 = false;
						}else if(choice.equals("no")){
							orderSuccess(seTime, addr, totalPrice, orderId, username, psw);
						}else{
							System.out.println("\t\t\t����������������������");
						}
					}
				}else {
					boolean circle2 = true;
					orderDao.insertOrder1(orderId, foodName, orderNum);
					while(circle2) {
						System.out.print("\t\t\t�Ƿ�Ҫ�������� yes/no:");
						String choice = input.next();
						if(choice.equals("yes")){
							circle2 = false;
						}else if(choice.equals("no")){
							orderSuccess(seTime, addr, totalPrice, orderId, username, psw);
						}else{
							System.out.println("\t\t\t����������������������");
						}
					}
				}
			}else if(state==0){
				System.out.println("\t\t\t�ò�Ʒ�Ѿ��¼ܣ�������ѡ��");
			}
		}
	}

	//���ͳɹ�
	public void orderSuccess(String seTime,String addr,float totalPrice,int orderId,String username,String psw) throws Exception {
		System.out.print("\t\t\t�������Ͳ�ʱ��(8:00-22:00):");
		seTime = input.next();
		System.out.print("\t\t\t���������ĵ�ַ:");
		addr = input.next();
		orderDao.insertOrderInfo(seTime, addr,totalPrice,orderId);
		System.out.print("\t\t\t���ͳɹ���ף���ò���죡\n");
		userr(username, psw);
	}
	
	//�˿Ͳ鿴����
	public void userLookOrder(String username) throws Exception{
		ArrayList<Order> alist1 = orderDao.userLookOrder(username);
		if(alist1.size()>0){
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}else{
			System.out.println("\t\t\t����û�п��Բ�ѯ�Ķ����ޣ��Ͽ�ȥ���Ͱ�~");
		}
	}
	
	//�û�ǩ�ն���
	public void signOrder(String username,String psw) throws Exception{
		boolean circle = true;	
		ArrayList<Order> alist1 = orderDao.cancleAndUpOrder(username);
		if(alist1.size()>0){		//����п���ǩ�յĶ���
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}
		else{
			System.out.println("\t\t\t����û�п���ǩ�յĶ���Ŷ~");
			userr(username,psw);
		}
		do{
			System.out.print("\t\t\t��������Ҫǩ�ն����Ķ������:");
			int orderId = input.nextInt();
			int state = orderDao.ifExistSignAndCancle(orderId, username);
			if(state==1) {
				orderDao.signOrder(orderId);
				System.out.println("\t\t\tǩ�ճɹ���лл���Ĺ���~");
				circle = false;
			}else {
				System.out.println("\t\t\t�ñ�Ų�����");
			}
		}while(circle);
	}
	
	//�û�ȡ������
	public void cancleOrder(String username,String psw) throws Exception{
		boolean circle = true;	
		ArrayList<Order> alist1 = orderDao.cancleAndUpOrder(username);
		if(alist1.size()>0){		//����п���ȡ���Ķ���
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}else{
			System.out.println("\t\t\t����û�п���ȡ���Ķ���Ŷ~");
			userr(username,psw);
		}
		do{
			System.out.print("\t\t\t��������Ҫȡ�������Ķ������:");
			int orderId = input.nextInt();
			int state = orderDao.ifExistSignAndCancle(orderId, username);
			if(state==1) {
				orderDao.cancleOrder(orderId);
				System.out.println("\t\t\tȡ���ɹ�����ӭ�´ι���");
				circle = false;
			}else {
				System.out.println("\t\t\t�ñ�Ų�����");
			}	
		}while(circle);
	}
	
	//�û�����
	public void upFood(String username,String psw) throws Exception{
		boolean circle = true;
		ArrayList<Order> alist1 = orderDao.goodOrder(username);
		if(alist1.size()>0){		//����п��Ե��޵Ķ���
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}else{
				System.out.println("\t\t\t����û�п��Ե��޵Ķ���Ŷ~");
				userr(username,psw);
			}
		do{
			System.out.print("\t\t\t��������Ҫ���޲�Ʒ�Ķ������:");
			int orderId = input.nextInt();
			int num = orderDao.goodOrderNum(username,orderId);
			int state = orderDao.ifExistUp(orderId,username);
			if(num>1) {		//�����ͬ�Ķ�����Ÿ�������1
				if(state==1) {		//����ö�����Ŵ���
					System.out.print("\t\t\t��������Ҫ���޲�Ʒ�Ĳ�Ʒ���ƣ�");
					String foodName = input.next();
					int exist = orderDao.foodNameUp(foodName, username);
					if(exist==1) {		//�����Ʒ����Ҳ����
						foodDao.upFood1(foodName, orderId);
						System.out.println("\t\t\t���޳ɹ���лл����֧��~");
						circle = false;
					}else {
						System.out.println("\t\t\t�ò�Ʒ������");
					}
				}else {
					System.out.println("\t\t\t�ö�����Ų�����");
				}
			}else {
				if(state==1) {
					foodDao.upFood2( orderId);
					System.out.println("\t\t\t���޳ɹ���лл����֧��~");
					circle = false;
				}else {
					System.out.println("\t\t\t�ö�����Ų�����");
				}
			}
		}while(circle);
	}

	//�û��޸�����
	public void userUpdatePsw(String username,String psw) throws Exception{
		boolean circle = true;
		do{
			System.out.print("\t\t\t���������ľ�����:");
			String oldPsw = input.next();
			boolean circle1 = true;
			do {
				if(oldPsw.equals(psw)){
					System.out.print("\t\t\t����������������:");
					String newPsw1 = input.next();
					System.out.print("\t\t\t���ٴ�������������:");
					String newPsw2 = input.next();
					if(newPsw1.equals(newPsw2)){
						userrDao.updatePsw(newPsw1,username);
						System.out.println("\t\t\t�����޸ĳɹ���");
						mainInter.enter();
					}else{
						System.out.print("\t\t\t�������벻ͬ������������:\n");
						break;
					}
				}else{
					System.out.print("\t\t\t���벻��ȷ������������:\n");
					circle1 = false;
				}
			}while(circle1);
		}while(circle);
	}
	
	//�û��޸��ֻ���
	public void userUpdateTel(String username) throws Exception{
		boolean circle = true;
		do{
			System.out.print("\t\t\t��������Ҫ�޸ĵ��ֻ���:");
			String tel = input.next();
			if(tel.length()!=11){
				System.out.println("\t\t\t��������ֻ��Ų���ȷ������������");
				userUpdateTel(username);
			}else{
				userrDao.updateTel(tel, username);
				System.out.println("\t\t\t�޸ĳɹ���");
				break;
			}
		}while(circle);
	}

	//�û��޸ĵ�ַ
	public void updateAddr(String username) throws Exception{
		ArrayList<Order> alist = orderDao.cancleAndUpOrder(username);
		if(alist.size()>0) {
			System.out.println("\t\t\t�����ڿ��޸ĵ�ַ�Ķ�����Ϣ���£�");
			for(int i=0;i<alist.size();i++){
				System.out.println("\t\t\t"+alist.get(i));
			}
			System.out.print("\t\t\t��������Ҫ�޸ĵ�ַ�Ķ�����ţ�");
			int orderId = input.nextInt();
			System.out.print("\t\t\t��������Ҫ�޸ĵĵ�ַ��");
			String addr = input.next();
			orderDao.updateAddr(orderId,addr);
			System.out.println("\t\t\t��ַ�޸ĳɹ���");
		}else {
			System.out.println("\t\t\t��û�п����޸ĵ�ַ�Ķ���Ŷ~");
		}
	}

}
