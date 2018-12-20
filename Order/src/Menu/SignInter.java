package Menu;

import java.util.Scanner;

import model.Food;
import model.Order;
import model.Userr;
import dao.FoodDao;
import dao.OrderDao;
import dao.UserrDao;

public class SignInter {
	UserrDao userrDao=new UserrDao();
	Userr userr=new Userr();
	OrderDao orderDao=new OrderDao();
	Order order=new Order();
	FoodDao foodDao=new FoodDao();
	Food food=new Food();
	Scanner input=new Scanner(System.in);
	//注册 
	public void sign() throws Exception {
		boolean circle=true;
		do {
			System.out.print("\t\t\t请输入您的名字:");
			String userrName=input.next();
			System.out.print("\t\t\t请输入您的密码:");
			String psw1=input.next();
			System.out.print("\t\t\t请再次输入您的密码:");
			String psw2=input.next();
			if(psw1.equals(psw2)) {
				userr.setUserrName(userrName);
				userr.setPsw(psw1);
			}else {
				System.out.print("\t\t\t您两次输入的密码不同！\n");
				sign();
			}
			System.out.print("\t\t\t请输入您的电话:");
			String tel = input.next();
			if(tel.length()!=11) {
				System.out.println("\t\t\t手机号码不正确！");
				sign();
			}else {
				userr.setTel(tel);
				userrDao.sign(userr);
				System.out.println("\t\t\t注册成功！\n");
				circle=false;
			}
		}while(circle);
	}
}
