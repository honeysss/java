package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Order;

public class OrderDao extends Conn {
	//�˿�ǩ�ն���
	public void signOrder(int orderId) throws Exception{
		String sql="update OrderInfo set seState=1 where orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//ͨ���û�����ѯ�˿Ϳ��Ե��޵Ķ���(�Ͳ�״̬Ϊ1 �Ƿ�ȡ��Ϊ0��
	public ArrayList<Order> goodOrder(String userName) throws Exception{
		String sql = "select * from Orderr,OrderInfo where orderName=? and seState=1 and cancle=0 and Orderr.orderId=OrderInfo.orderId";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet set = ps.executeQuery();
		ArrayList<Order> alist = new ArrayList<Order>();
		while(set.next()){
			int orderId = set.getInt(1);
			String foodName = set.getString(2);
			int orderNum = set.getInt(3);
			String orderName = set.getString(5);
			String seTime = set.getString(6);
			int seState = set.getInt(7);
			String addr = set.getString(8);
			int cancle = set.getInt(9);
			float totalPrice = set.getFloat(10);
			Order order = new Order(orderId, foodName, orderNum, orderName, seTime, seState, addr, cancle, totalPrice);
			alist.add(order);
		}
		getConnection().close();
		return alist;
	}
	
	//ͨ���û�����ѯ�˿Ϳ���ǩ�պ�ȡ���Ķ���(�Ͳ�״̬Ϊ0 �Ƿ�ȡ��Ϊ0��
	public ArrayList<Order> cancleAndUpOrder(String userName) throws Exception{
		String sql = "select * from Orderr,OrderInfo where orderName=? and seState=0 and cancle=0 and Orderr.orderId=OrderInfo.orderId";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet set = ps.executeQuery();
		ArrayList<Order> alist = new ArrayList<Order>();
		while(set.next()){
			int orderId = set.getInt(1);
			String foodName = set.getString(2);
			int orderNum = set.getInt(3);
			String orderName = set.getString(5);
			String seTime = set.getString(6);
			int seState = set.getInt(7);
			String addr = set.getString(8);
			int cancle = set.getInt(9);
			float totalPrice = set.getFloat(10);
			Order order = new Order(orderId, foodName, orderNum, orderName, seTime, seState, addr, cancle, totalPrice);
			alist.add(order);
		}
		getConnection().close();
		return alist;
	}

	//ͨ���û����Ͷ�����Ų�ѯ���Ե��޵Ķ�������ͬ�Ķ�����Ÿ���
	public int goodOrderNum(String userName,int orderId) throws Exception{
		String sql = "select count(Orderr.orderId) from Orderr,OrderInfo where cancle=0 and seState=1 and orderName=? and Orderr.orderId=? and Orderr.orderId=OrderInfo.orderId";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, userName);
		ps.setInt(2, orderId);
		ResultSet set = ps.executeQuery();
		int num = 0;
		if(set.next()){
			num = set.getInt(1);
		}
		getConnection().close();
		return num;
	}
	
	//�˿�ȡ������
	public void cancleOrder(int orderId) throws Exception{
		String sql="update OrderInfo set cancle=1 where orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		getConnection().close();
	}

	//�û��޸ĵ�ַ
	public void updateAddr(int orderId,String addr) throws Exception{
			String sql="update OrderInfo set addr=? where orderId=?";
			PreparedStatement ps=getConnection().prepareStatement(sql);
			ps.setString(1, addr);
			ps.setInt(2, orderId);
			ps.executeUpdate();
			getConnection().close();
		}
	

	//ͨ���û�����Ĳ�Ʒ���Ʋ鿴�ò�Ʒ�Ƿ��ڿɵ��޵Ķ�����
	public int  foodNameUp(String foodName,String userName) throws Exception{
		String sql = "select * from Orderr,OrderInfo where foodName=? and orderName=? and cancle=0 and seState=1";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setString(2, userName);
		ResultSet set = ps.executeQuery();
		int state = 0;
		if(set.next()){
			state = 1;
		}
		getConnection().close();
		return state;
	}

	
	//ͨ���û�����Ķ��ͱ�Ų�ѯ�ö��ͱ���Ƿ��ڿ�ǩ�պ�ȡ���Ķ�����
	public int ifExistSignAndCancle(int orderId,String userName) throws Exception{
		String sql = "select * from Orderr,OrderInfo where Orderr.orderId=? and orderName=? and seState=0 and cancle=0";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.setString(2, userName);
		ResultSet set = ps.executeQuery();
		int state = 0;
		if(set.next()){
			state = 1;
		}
		getConnection().close();
		return state;
	}
	
	//ͨ���û�����Ķ��ͱ�Ų�ѯ�ö��ͱ���Ƿ��ڿɵ��޵Ķ�����
	public int  ifExistUp(int orderId,String userName) throws Exception{
		String sql = "select * from Orderr,OrderInfo where OrderInfo.orderId=? and orderName=? and cancle=0 and seState=1";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.setString(2, userName);
		ResultSet set = ps.executeQuery();
		int state = 0;
		if(set.next()){
			state = 1;
		}
		getConnection().close();
		return state;
	}

	//����Ա�鿴���ж���
	public ArrayList<Order> lookAllOrder() throws Exception{
		String sql = "select * from Orderr,OrderInfo where Orderr.orderId=OrderInfo.orderId";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ResultSet set = ps.executeQuery();
		ArrayList<Order> alist = new ArrayList<Order>();
		while(set.next()){
			int orderId = set.getInt(1);
			String foodName = set.getString(2);
			int orderNum = set.getInt(3);
			String orderName = set.getString(5);
			String seTime = set.getString(6);
			int seState = set.getInt(7);
			String addr = set.getString(8);
			int cancle = set.getInt(9);
			float totalPrice = set.getFloat(10);
			Order order = new Order(orderId, foodName, orderNum, orderName, seTime, seState, addr, cancle, totalPrice);
			alist.add(order);
		}
		getConnection().close();
		return alist;
	}
	
	//�˿Ͳ鿴����
	public ArrayList<Order> userLookOrder(String username) throws Exception{
		String sql = "select * from Orderr,OrderInfo where orderName=? and Orderr.orderId=OrderInfo.orderId";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, username);
		ResultSet set = ps.executeQuery();
		ArrayList<Order> alist = new ArrayList<Order>();
		while(set.next()){
			int orderId = set.getInt(1);
			String foodName = set.getString(2);
			int orderNum = set.getInt(3);
			String orderName = set.getString(5);
			String seTime = set.getString(6);
			int seState = set.getInt(7);
			String addr = set.getString(8);
			int cancle = set.getInt(9);
			float totalPrice = set.getFloat(10);
			Order order = new Order(orderId, foodName, orderNum, orderName, seTime, seState, addr, cancle, totalPrice);
			alist.add(order);
		}
		getConnection().close();
		return alist;
	}

	//�û�ֻҪѡ����Ҫ���ͣ����Զ�����һ��������� ��OrderInfo����
	public void creatOrder(String userName) throws Exception{
		String sql = "insert into OrderInfo(orderName) values(?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, userName);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//�������ͬ�Ķ�������������ۼ�
	public void orderNumAdd(int orderNum,String foodName,int orderId) throws Exception {
		String sql = "update Orderr set orderNum=orderNum+? where foodName=? and orderId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, orderNum);
		ps.setString(2,foodName);
		ps.setInt(3, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//���û�������Ϣ���ҵ����ζ����Ķ������
	public int findId(String userName) throws Exception{
		String sql = "select orderId from OrderInfo where orderName=? order by orderId desc";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet set = ps.executeQuery();
		int orderId = 0;
		if(set.next()){
			orderId = set.getInt(1);
		}
		getConnection().close();
		return orderId;
	}
	
	//���û��Ķ�����Ϣ���뵽��������  ��Orderr����
	public void insertOrder1(int orderId,String foodName,int orderNum) throws Exception{
		String sql = "insert into Orderr values(?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.setString(2, foodName);
		ps.setInt(3, orderNum);
		ps.executeUpdate();
		getConnection().close();
	}
				
	//���û��ĵ�ַ���Ͳ�ʱ�䡢��������Ը��µķ�ʽ���뵽OrderInfo����(ͨ���������)
	public void insertOrderInfo(String seTime,String addr,float totalPrice,int orderId) throws Exception{
		String sql = "update OrderInfo set seTime=?,addr=?,totalPrice=? where orderId=? ";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, seTime);
		ps.setString(2, addr);
		ps.setFloat(3, totalPrice);
		ps.setInt(4, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	

	
}
