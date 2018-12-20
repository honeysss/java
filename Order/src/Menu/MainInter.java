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
	
	//��ʼ  
	public void start() throws Exception{
		boolean circle=true;
		do{
			System.out.println("\t\t\t----��ӭʹ�óԻ����˶���ϵͳ----");
			System.out.println("\t\t\t  1����½     2��ע��     3���˳�");
			System.out.print("\t\t\t��ѡ����:");
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
					System.out.println("\t\t\t��ӭ�´ι��٣�");
					System.exit(-1);
				default:
					System.out.println("\t\t\tû�д�ѡ��");
					break;
			}
		}while(circle);
	}
	
	//��½
	public void enter() throws Exception{
		System.out.print("\t\t\t�������û���:");
		String userName=input.next();
		System.out.print("\t\t\t����������:");
		String psw=input.next();
		userr = userrDao.select(userName, psw);
		
		if (userr != null) {
			if(userr.getIdentify()==0) {		//�����ϢΪ����Ա
				AdminInter adminInter = new AdminInter();
				adminInter.admin(userName,psw);
			}else {		//���Ϊ��ͨ�û�
				UserrInter userrInter = new UserrInter();
				userrInter.userr(userName,psw);
			}
		}else {		//�û����������벻ͬʱ����
			System.out.print("\t\t\t�����û��������벻��ȷ������������\n");
			enter();
		}
	}
	
}
