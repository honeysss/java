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

	//用户 
	public void userr(String username,String psw) throws Exception {
		boolean circle=true;
		do {
			System.out.println("\t\t\t----用户界面----");
			System.out.println("  \t\t\t1、查看菜品信息");
			System.out.println("  \t\t\t2、我要订餐");
			System.out.println("  \t\t\t3、查看订单");
			System.out.println("  \t\t\t4、签收订单");
			System.out.println("  \t\t\t5、取消订单");
			System.out.println("  \t\t\t6、我要点赞");
			System.out.println("  \t\t\t7、修改密码");
			System.out.println("  \t\t\t8、修改手机号");
			System.out.println("  \t\t\t9、修改地址");
			System.out.println("  \t\t\t10、返回上一层");
			System.out.print("  \t\t\t请选择您要进行的操作:");
			int choice3=input.nextInt();
			switch(choice3) {
				case 1:	
					//顾客查看菜品信息
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
					//顾客查看订单
					userLookOrder(username);
					break;
				case 4:
					//签收订单
					signOrder(username,psw);
					break;
				case 5:
					//取消订单
					cancleOrder(username,psw);
					break;
				case 6:
					//点赞
					upFood(username,psw);
					break;
				case 7:
					//用户修改密码
					userUpdatePsw(username,psw);
					circle = false;
				case 8:
					//用户修改手机号
					userUpdateTel(username);
					break;
				case 9:
					//修改地址
					updateAddr(username);
					break;
				case 10:
					mainInter.start();
					break;
				default:
					System.out.println("\t\t\t没有此选项，请重新选择:");
					break;
			}
		}while(circle);
		
	}

	//用户定餐
	private void eat(String username,String psw) throws Exception{
		orderDao.creatOrder(username);
		int orderId = orderDao.findId(username);
		boolean circle=true;
		float totalPrice = 0;
		String seTime = null;
		String addr = null;
		System.out.println("\t\t\t菜品信息如下:");
		ArrayList<Food> alist1 = foodDao.selectFood();
		for(int i=0;i<alist1.size();i++){
			System.out.println("\t\t\t"+alist1.get(i));
		}
		while(circle){
			System.out.print("\t\t\t请输入菜品编号:");
			int foodId = input.nextInt();
			Food food = foodDao.idName(foodId);
			String foodName = food.getFoodName();
			float price = food.getPrice();
			int state = foodDao.idState(foodId);
			if(state == 1){
				System.out.print("\t\t\t请输入份数:");
				int orderNum = input.nextInt();
				totalPrice = totalPrice+price*orderNum;
				int num = foodDao.foodIdFind(foodName, orderId);
				if(num==1) {	//如果有相同的订单（订单编号，订餐人都相同）
					boolean circle3 = true;
					orderDao.orderNumAdd(orderNum, foodName, orderId);
					while(circle3) {
						System.out.print("\t\t\t是否还要继续定餐 yes/no:");
						String choice = input.next();
						if(choice.equals("yes")){
							circle3 = false;
						}else if(choice.equals("no")){
							orderSuccess(seTime, addr, totalPrice, orderId, username, psw);
						}else{
							System.out.println("\t\t\t您的输入有误，请重新输入");
						}
					}
				}else {
					boolean circle2 = true;
					orderDao.insertOrder1(orderId, foodName, orderNum);
					while(circle2) {
						System.out.print("\t\t\t是否还要继续定餐 yes/no:");
						String choice = input.next();
						if(choice.equals("yes")){
							circle2 = false;
						}else if(choice.equals("no")){
							orderSuccess(seTime, addr, totalPrice, orderId, username, psw);
						}else{
							System.out.println("\t\t\t您的输入有误，请重新输入");
						}
					}
				}
			}else if(state==0){
				System.out.println("\t\t\t该菜品已经下架，请重新选购");
			}
		}
	}

	//订餐成功
	public void orderSuccess(String seTime,String addr,float totalPrice,int orderId,String username,String psw) throws Exception {
		System.out.print("\t\t\t请输入送餐时间(8:00-22:00):");
		seTime = input.next();
		System.out.print("\t\t\t请输入您的地址:");
		addr = input.next();
		orderDao.insertOrderInfo(seTime, addr,totalPrice,orderId);
		System.out.print("\t\t\t定餐成功，祝您用餐愉快！\n");
		userr(username, psw);
	}
	
	//顾客查看订单
	public void userLookOrder(String username) throws Exception{
		ArrayList<Order> alist1 = orderDao.userLookOrder(username);
		if(alist1.size()>0){
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}else{
			System.out.println("\t\t\t您还没有可以查询的订单噢，赶快去定餐吧~");
		}
	}
	
	//用户签收订单
	public void signOrder(String username,String psw) throws Exception{
		boolean circle = true;	
		ArrayList<Order> alist1 = orderDao.cancleAndUpOrder(username);
		if(alist1.size()>0){		//如果有可以签收的订单
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}
		else{
			System.out.println("\t\t\t您还没有可以签收的订单哦~");
			userr(username,psw);
		}
		do{
			System.out.print("\t\t\t请输入您要签收订单的订单编号:");
			int orderId = input.nextInt();
			int state = orderDao.ifExistSignAndCancle(orderId, username);
			if(state==1) {
				orderDao.signOrder(orderId);
				System.out.println("\t\t\t签收成功，谢谢您的光临~");
				circle = false;
			}else {
				System.out.println("\t\t\t该编号不存在");
			}
		}while(circle);
	}
	
	//用户取消订单
	public void cancleOrder(String username,String psw) throws Exception{
		boolean circle = true;	
		ArrayList<Order> alist1 = orderDao.cancleAndUpOrder(username);
		if(alist1.size()>0){		//如果有可以取消的订单
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}else{
			System.out.println("\t\t\t您还没有可以取消的订单哦~");
			userr(username,psw);
		}
		do{
			System.out.print("\t\t\t请输入您要取消订单的订单编号:");
			int orderId = input.nextInt();
			int state = orderDao.ifExistSignAndCancle(orderId, username);
			if(state==1) {
				orderDao.cancleOrder(orderId);
				System.out.println("\t\t\t取消成功，欢迎下次光临");
				circle = false;
			}else {
				System.out.println("\t\t\t该编号不存在");
			}	
		}while(circle);
	}
	
	//用户点赞
	public void upFood(String username,String psw) throws Exception{
		boolean circle = true;
		ArrayList<Order> alist1 = orderDao.goodOrder(username);
		if(alist1.size()>0){		//如果有可以点赞的订单
			for(int i=0;i<alist1.size();i++){
				System.out.println("\t\t\t"+alist1.get(i));
			}
		}else{
				System.out.println("\t\t\t您还没有可以点赞的订单哦~");
				userr(username,psw);
			}
		do{
			System.out.print("\t\t\t请输入您要点赞菜品的订单编号:");
			int orderId = input.nextInt();
			int num = orderDao.goodOrderNum(username,orderId);
			int state = orderDao.ifExistUp(orderId,username);
			if(num>1) {		//如果相同的订单编号个数大于1
				if(state==1) {		//如果该订单编号存在
					System.out.print("\t\t\t请输入您要点赞菜品的菜品名称：");
					String foodName = input.next();
					int exist = orderDao.foodNameUp(foodName, username);
					if(exist==1) {		//如果菜品名称也存在
						foodDao.upFood1(foodName, orderId);
						System.out.println("\t\t\t点赞成功，谢谢您的支持~");
						circle = false;
					}else {
						System.out.println("\t\t\t该菜品不存在");
					}
				}else {
					System.out.println("\t\t\t该订单编号不存在");
				}
			}else {
				if(state==1) {
					foodDao.upFood2( orderId);
					System.out.println("\t\t\t点赞成功，谢谢您的支持~");
					circle = false;
				}else {
					System.out.println("\t\t\t该订单编号不存在");
				}
			}
		}while(circle);
	}

	//用户修改密码
	public void userUpdatePsw(String username,String psw) throws Exception{
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
	
	//用户修改手机号
	public void userUpdateTel(String username) throws Exception{
		boolean circle = true;
		do{
			System.out.print("\t\t\t请输入您要修改的手机号:");
			String tel = input.next();
			if(tel.length()!=11){
				System.out.println("\t\t\t您输入的手机号不正确，请重新输入");
				userUpdateTel(username);
			}else{
				userrDao.updateTel(tel, username);
				System.out.println("\t\t\t修改成功！");
				break;
			}
		}while(circle);
	}

	//用户修改地址
	public void updateAddr(String username) throws Exception{
		ArrayList<Order> alist = orderDao.cancleAndUpOrder(username);
		if(alist.size()>0) {
			System.out.println("\t\t\t您现在可修改地址的订单信息如下：");
			for(int i=0;i<alist.size();i++){
				System.out.println("\t\t\t"+alist.get(i));
			}
			System.out.print("\t\t\t请输入您要修改地址的订单编号：");
			int orderId = input.nextInt();
			System.out.print("\t\t\t请输入您要修改的地址：");
			String addr = input.next();
			orderDao.updateAddr(orderId,addr);
			System.out.println("\t\t\t地址修改成功！");
		}else {
			System.out.println("\t\t\t您没有可以修改地址的订单哦~");
		}
	}

}
