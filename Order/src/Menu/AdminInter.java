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
	//管理员 
	public void admin(String username,String psw) throws Exception{
		boolean circle=true;
		do {
			System.out.println("\t\t\t----管理员界面----");
			System.out.println("  \t\t\t1、查看菜品信息");
			System.out.println("  \t\t\t2、录入菜品");
			System.out.println("  \t\t\t3、菜品下架");
			System.out.println("  \t\t\t4、查看顾客订单");
			System.out.println("  \t\t\t5、修改单价");
			System.out.println("  \t\t\t6、菜品上架");
			System.out.println("  \t\t\t7、修改密码");
			System.out.println("  \t\t\t8、修改手机号");
			System.out.println("  \t\t\t9、修改菜品名称");
			System.out.println("  \t\t\t10、返回上一层");
			System.out.print("\t\t\t请选择您要进行的操作:");
			int choice2=input.nextInt();
			switch(choice2) {
				case 1:	
					//查看菜品信息
					ArrayList<Food> alist = new ArrayList<Food>();
					alist=foodDao.selectFood();
					for(int i=0;i<alist.size();i++){
						System.out.println("\t\t\t"+alist.get(i));
					}
					break;
				case 2:
					//录入菜品
					addFood();
					break;
				case 3:
					//下架菜品
					System.out.println("\t\t\t菜品信息如下：");
					ArrayList<Food> alist1 = foodDao.selectFood();
					for(int i=0;i<alist1.size();i++){
						System.out.println("\t\t\t"+alist1.get(i));
					}
					boolean circle3 = true;
					while(circle3){
						System.out.print("\t\t\t请输入您要下架菜品的菜单编号:");
						int foodId = input.nextInt();
						int exist1 = foodDao.idFoodId(foodId);
						if(exist1==1) {
							int state = foodDao.idState(foodId);
							if(state==0) {
								System.out.println("\t\t\t该菜品已经下架！");
							}else {
								foodDao.downFood(foodId);
								System.out.println("\t\t\t下架成功！");
								circle3 = false;
							}
						}else {
							System.out.println("\t\t\t该菜品编号不存在！");
						}
					}
					break;
				case 4:
					//管理员查看订单
					adminLook();
					break;
				case 5:
					//修改单价
					System.out.println("\t\t\t菜品信息如下:");
					ArrayList<Food> alist2 = new ArrayList<Food>();
					alist2=foodDao.selectFood();
					for(int i=0;i<alist2.size();i++){
						System.out.println("\t\t\t"+alist2.get(i));
					}
					boolean circle1 = true;
					while(circle1){
						System.out.print("\t\t\t请输入您要修改单价的菜品编号:");
						int foodId2 = input.nextInt();
						int exist = foodDao.idFoodId(foodId2);
						if(exist==1){
							System.out.print("\t\t\t请输入修改后的单价:");
							float price = input.nextFloat();
							foodDao.updateFood(foodId2, price);
							circle1 = false;
						}else{
							System.out.println("\t\t\t该菜品编号不存在！");
						}
					}
											
					break;
				case 6:
					//菜品上架
					System.out.println("\t\t\t菜品信息如下:");
					ArrayList<Food> alist11 = foodDao.selectFood();
					for(int i=0;i<alist11.size();i++){
						System.out.println("\t\t\t"+alist11.get(i));
					}
					boolean circle2 = true;
					while(circle2){
						System.out.print("\t\t\t请输入您要上架菜品的菜单编号:");
						int foodId3 = input.nextInt();
						int exist = foodDao.idFoodId(foodId3);
						if(exist==1) {
							int state1 = foodDao.idState(foodId3);
							if(state1==1) {
								System.out.println("\t\t\t该菜品已经上架！");
							}else {
								foodDao.reShelf(foodId3);
								System.out.println("\t\t\t上架成功！");
								circle2= false;
							}
						}else {
							System.out.println("\t\t\t该菜品编号不存在！");
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
					//修改菜品名称
					boolean circle11 = true;
					System.out.println("\t\t\t菜品信息如下：");
					ArrayList<Food> alist3 = new ArrayList<Food>();
					alist3=foodDao.selectFood();
					for(int i=0;i<alist3.size();i++){
						System.out.println("\t\t\t"+alist3.get(i));
					}
					while(circle11){
						System.out.print("\t\t\t请输入您想要修改菜品名称的菜品编号：");
						int foodId1 = input.nextInt();
						int exist2 = foodDao.idFoodId(foodId1);
						if(exist2==1){
							System.out.print("\t\t\t请输入修改后的菜品名称：");
							String foodName = input.next();
							int exist3 = foodDao.nameFindName(foodName,foodId1);
							if(exist3!=1){
								foodDao.updateFoodName(foodName, foodId1);
								System.out.println("\t\t\t修改成功！");
								circle11 = false;
							}else{
								System.out.println("\t\t\t菜品名称重复了噢");
							}
						}else{
							System.out.println("\t\t\t没有该菜品编号！");
						}
					}										
					break;
				case 10:
					mainInter.start();
					break;
				default:
					System.out.print("\t\t\t没有此选项，请重新选择\n");
					break;
			}
		}while(circle);
	}

	//管理员修改密码
	public void adminUpdatePsw(String username,String psw) throws Exception{
		boolean circle = true;
		do{
			System.out.print("\t\t\t请输入您的旧密码:");
			String oldPsw = input.next();
			boolean circle1 = true;
			do {
				if(oldPsw.equals(psw)){
					System.out.print("\t\t\t请输入您的新密码:");
					String newPsw1 = input.next();
					System.out.print("\t\t\t请再次输入您的密码:");
					String newPsw2 = input.next();
					if(newPsw1.equals(newPsw2)){
						userrDao.updatePsw(newPsw1,username);
						System.out.println("\t\t\t密码修改成功！");
						mainInter.enter();
					}else{
						System.out.print("\t\t\t两次密码不同，请重新输入:\n");
						break;
					}
				}else{
					System.out.print("\t\t\t密码不正确，请重新输入:\n");
					circle1 = false;
				}
			}while(circle1);
		}while(circle);
	}
	
	//管理员修改手机号
	public void adminUpdateTel(String username) throws Exception{
		boolean circle = true;
		do{
			System.out.print("\t\t\t请输入您要修改的手机号:");
			String tel = input.next();
			if(tel.length()!=11){
				System.out.println("\t\t\t您输入的手机号不正确，请重新输入");
				userrInter.userUpdateTel(username);
			}else{
				userrDao.updateTel(tel, username);
				System.out.println("\t\t\t手机号修改成功！");
				circle = false;
				break;
			}
		}while(circle);
	}
	
	//管理员查看订单
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
				System.out.println("\t\t\t还没有订单可以查询");
				circle = false;
			}
		}while(circle);
	}
	
	//录入菜品
	public void addFood() throws Exception{
		System.out.print("\t\t\t请输入菜品名称:");
		String foodName = input.next();
		int num = foodDao.foodNameNum(foodName);
		if(num>0) {
			System.out.println("\t\t\t菜单中已经有该菜品了！");
			addFood();
		}else {
			System.out.print("\t\t\t请输入单价:");
			float price = input.nextFloat();
			food.setFoodName(foodName);
			food.setPrice(price);
			foodDao.insertFood(food);
			System.out.println("\t\t\t录入成功，目前菜品信息如下:");ArrayList<Food> alist = new ArrayList<Food>();
			alist = foodDao.selectFood();
			for(int i=0;i<alist.size();i++){
				System.out.println("\t\t\t"+alist.get(i));
			}
		}
	}

}
