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
	//ע�� 
	public void sign() throws Exception {
		boolean circle=true;
		do {
			System.out.print("\t\t\t��������������:");
			String userrName=input.next();
			System.out.print("\t\t\t��������������:");
			String psw1=input.next();
			System.out.print("\t\t\t���ٴ�������������:");
			String psw2=input.next();
			if(psw1.equals(psw2)) {
				userr.setUserrName(userrName);
				userr.setPsw(psw1);
			}else {
				System.out.print("\t\t\t��������������벻ͬ��\n");
				sign();
			}
			System.out.print("\t\t\t���������ĵ绰:");
			String tel = input.next();
			if(tel.length()!=11) {
				System.out.println("\t\t\t�ֻ����벻��ȷ��");
				sign();
			}else {
				userr.setTel(tel);
				userrDao.sign(userr);
				System.out.println("\t\t\tע��ɹ���\n");
				circle=false;
			}
		}while(circle);
	}
}
