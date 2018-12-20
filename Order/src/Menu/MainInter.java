package Menu;

import java.util.Scanner;

import dao.FoodDao;
import dao.OrderDao;
import dao.UserrDao;
import model.Food;
import model.Order;
import model.Userr;
public class MainInter {
	UserrDao userrDao=new UserrDao();
	Userr userr=new Userr();
	OrderDao orderDao=new OrderDao();
	Order order=new Order();
	FoodDao foodDao=new FoodDao();
	Food food=new Food();
	Scanner input=new Scanner(System.in);
	
	//开始  
	public void start() throws Exception{
		boolean circle=true;
		do{
			System.out.println("\t\t\t----欢迎使用吃货联盟订餐系统----");
			System.out.println("\t\t\t  1、登陆     2、注册     3、退出");
			System.out.print("\t\t\t请选择功能:");
			int choice1=input.nextInt();
			switch(choice1){
				case 1:
					enter();
					break;
				case 2:
					SignInter singnInter = new SignInter();
					singnInter.sign();
					break;
				case 3:
					System.out.println("\t\t\t欢迎下次光临！");
					System.exit(-1);
				default:
					System.out.println("\t\t\t没有此选项");
					break;
			}
		}while(circle);
	}
	
	//登陆
	public void enter() throws Exception{
		System.out.print("\t\t\t请输入用户名:");
		String userName=input.next();
		System.out.print("\t\t\t请输入密码:");
		String psw=input.next();
		userr = userrDao.select(userName, psw);
		
		if (userr != null) {
			if(userr.getIdentify()==0) {		//身份信息为管理员
				AdminInter adminInter = new AdminInter();
				adminInter.admin(userName,psw);
			}else {		//身份为普通用户
				UserrInter userrInter = new UserrInter();
				userrInter.userr(userName,psw);
			}
		}else {		//用户名或者密码不同时存在
			System.out.print("\t\t\t您的用户名或密码不正确，请重新输入\n");
			enter();
		}
	}
	
}
