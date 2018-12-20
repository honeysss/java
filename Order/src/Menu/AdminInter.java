package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import model.Food;
import model.Order;
import model.Userr;
import dao.FoodDao;
import dao.OrderDao;
import dao.UserrDao;

public class AdminInter {
	UserrDao userrDao=new UserrDao();
	Userr userr=new Userr();
	OrderDao orderDao=new OrderDao();
	Order order=new Order();
	FoodDao foodDao=new FoodDao();
	Food food=new Food();
	Scanner input=new Scanner(System.in);
	MainInter mainInter = new MainInter();
	UserrInter userrInter = new UserrInter();
	//����Ա 
	public void admin(String username,String psw) throws Exception{
		boolean circle=true;
		do {
			System.out.println("\t\t\t----����Ա����----");
			System.out.println("  \t\t\t1���鿴��Ʒ��Ϣ");
			System.out.println("  \t\t\t2��¼���Ʒ");
			System.out.println("  \t\t\t3����Ʒ�¼�");
			System.out.println("  \t\t\t4���鿴�˿Ͷ���");
			System.out.println("  \t\t\t5���޸ĵ���");
			System.out.println("  \t\t\t6����Ʒ�ϼ�");
			System.out.println("  \t\t\t7���޸�����");
			System.out.println("  \t\t\t8���޸��ֻ���");
			System.out.println("  \t\t\t9���޸Ĳ�Ʒ����");
			System.out.println("  \t\t\t10��������һ��");
			System.out.print("\t\t\t��ѡ����Ҫ���еĲ���:");
			int choice2=input.nextInt();
			switch(choice2) {
				case 1:	
					//�鿴��Ʒ��Ϣ
					ArrayList<Food> alist = new ArrayList<Food>();
					alist=foodDao.selectFood();
					for(int i=0;i<alist.size();i++){
						System.out.println("\t\t\t"+alist.get(i));
					}
					break;
				case 2:
					//¼���Ʒ
					addFood();
					break;
				case 3:
					//�¼ܲ�Ʒ
					System.out.println("\t\t\t��Ʒ��Ϣ���£�");
					ArrayList<Food> alist1 = foodDao.selectFood();
					for(int i=0;i<alist1.size();i++){
						System.out.println("\t\t\t"+alist1.get(i));
					}
					boolean circle3 = true;
					while(circle3){
						System.out.print("\t\t\t��������Ҫ�¼ܲ�Ʒ�Ĳ˵����:");
						int foodId = input.nextInt();
						int exist1 = foodDao.idFoodId(foodId);
						if(exist1==1) {
							int state = foodDao.idState(foodId);
							if(state==0) {
								System.out.println("\t\t\t�ò�Ʒ�Ѿ��¼ܣ�");
							}else {
								foodDao.downFood(foodId);
								System.out.println("\t\t\t�¼ܳɹ���");
								circle3 = false;
							}
						}else {
							System.out.println("\t\t\t�ò�Ʒ��Ų����ڣ�");
						}
					}
					break;
				case 4:
					//����Ա�鿴����
					adminLook();
					break;
				case 5:
					//�޸ĵ���
					System.out.println("\t\t\t��Ʒ��Ϣ����:");
					ArrayList<Food> alist2 = new ArrayList<Food>();
					alist2=foodDao.selectFood();
					for(int i=0;i<alist2.size();i++){
						System.out.println("\t\t\t"+alist2.get(i));
					}
					boolean circle1 = true;
					while(circle1){
						System.out.print("\t\t\t��������Ҫ�޸ĵ��۵Ĳ�Ʒ���:");
						int foodId2 = input.nextInt();
						int exist = foodDao.idFoodId(foodId2);
						if(exist==1){
							System.out.print("\t\t\t�������޸ĺ�ĵ���:");
							float price = input.nextFloat();
							foodDao.updateFood(foodId2, price);
							circle1 = false;
						}else{
							System.out.println("\t\t\t�ò�Ʒ��Ų����ڣ�");
						}
					}
											
					break;
				case 6:
					//��Ʒ�ϼ�
					System.out.println("\t\t\t��Ʒ��Ϣ����:");
					ArrayList<Food> alist11 = foodDao.selectFood();
					for(int i=0;i<alist11.size();i++){
						System.out.println("\t\t\t"+alist11.get(i));
					}
					boolean circle2 = true;
					while(circle2){
						System.out.print("\t\t\t��������Ҫ�ϼܲ�Ʒ�Ĳ˵����:");
						int foodId3 = input.nextInt();
						int exist = foodDao.idFoodId(foodId3);
						if(exist==1) {
							int state1 = foodDao.idState(foodId3);
							if(state1==1) {
								System.out.println("\t\t\t�ò�Ʒ�Ѿ��ϼܣ�");
							}else {
								foodDao.reShelf(foodId3);
								System.out.println("\t\t\t�ϼܳɹ���");
								circle2= false;
							}
						}else {
							System.out.println("\t\t\t�ò�Ʒ��Ų����ڣ�");
						}
					}
					break;
				case 7:
					adminUpdatePsw(username, psw);
					break;
				case 8:
					adminUpdateTel(username);
					break;
				case 9:
					//�޸Ĳ�Ʒ����
					boolean circle11 = true;
					System.out.println("\t\t\t��Ʒ��Ϣ���£�");
					ArrayList<Food> alist3 = new ArrayList<Food>();
					alist3=foodDao.selectFood();
					for(int i=0;i<alist3.size();i++){
						System.out.println("\t\t\t"+alist3.get(i));
					}
					while(circle11){
						System.out.print("\t\t\t����������Ҫ�޸Ĳ�Ʒ���ƵĲ�Ʒ��ţ�");
						int foodId1 = input.nextInt();
						int exist2 = foodDao.idFoodId(foodId1);
						if(exist2==1){
							System.out.print("\t\t\t�������޸ĺ�Ĳ�Ʒ���ƣ�");
							String foodName = input.next();
							int exist3 = foodDao.nameFindName(foodName,foodId1);
							if(exist3!=1){
								foodDao.updateFoodName(foodName, foodId1);
								System.out.println("\t\t\t�޸ĳɹ���");
								circle11 = false;
							}else{
								System.out.println("\t\t\t��Ʒ�����ظ�����");
							}
						}else{
							System.out.println("\t\t\tû�иò�Ʒ��ţ�");
						}
					}										
					break;
				case 10:
					mainInter.start();
					break;
				default:
					System.out.print("\t\t\tû�д�ѡ�������ѡ��\n");
					break;
			}
		}while(circle);
	}

	//����Ա�޸�����
	public void adminUpdatePsw(String username,String psw) throws Exception{
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
	
	//����Ա�޸��ֻ���
	public void adminUpdateTel(String username) throws Exception{
		boolean circle = true;
		do{
			System.out.print("\t\t\t��������Ҫ�޸ĵ��ֻ���:");
			String tel = input.next();
			if(tel.length()!=11){
				System.out.println("\t\t\t��������ֻ��Ų���ȷ������������");
				userrInter.userUpdateTel(username);
			}else{
				userrDao.updateTel(tel, username);
				System.out.println("\t\t\t�ֻ����޸ĳɹ���");
				circle = false;
				break;
			}
		}while(circle);
	}
	
	//����Ա�鿴����
	public void adminLook() throws Exception{
		boolean circle = true;
		do{
			ArrayList<Order> alist = orderDao.lookAllOrder();
			if(alist.size()>0){
				for(int i=0;i<alist.size();i++){
					System.out.println("\t\t\t"+alist.get(i));
				}
				circle = false;
			}else{
				System.out.println("\t\t\t��û�ж������Բ�ѯ");
				circle = false;
			}
		}while(circle);
	}
	
	//¼���Ʒ
	public void addFood() throws Exception{
		System.out.print("\t\t\t�������Ʒ����:");
		String foodName = input.next();
		int num = foodDao.foodNameNum(foodName);
		if(num>0) {
			System.out.println("\t\t\t�˵����Ѿ��иò�Ʒ�ˣ�");
			addFood();
		}else {
			System.out.print("\t\t\t�����뵥��:");
			float price = input.nextFloat();
			food.setFoodName(foodName);
			food.setPrice(price);
			foodDao.insertFood(food);
			System.out.println("\t\t\t¼��ɹ���Ŀǰ��Ʒ��Ϣ����:");ArrayList<Food> alist = new ArrayList<Food>();
			alist = foodDao.selectFood();
			for(int i=0;i<alist.size();i++){
				System.out.println("\t\t\t"+alist.get(i));
			}
		}
	}

}
