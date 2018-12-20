package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Order;

public class OrderDao extends Conn {
	//顾客签收订单
	public void signOrder(int orderId) throws Exception{
		String sql="update OrderInfo set seState=1 where orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//通过用户名查询顾客可以点赞的订单(送餐状态为1 是否取消为0）
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
	
	//通过用户名查询顾客可以签收和取消的订单(送餐状态为0 是否取消为0）
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

	//通过用户名和订单编号查询可以点赞的订单中相同的订单编号个数
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
	
	//顾客取消订单
	public void cancleOrder(int orderId) throws Exception{
		String sql="update OrderInfo set cancle=1 where orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		getConnection().close();
	}

	//用户修改地址
	public void updateAddr(int orderId,String addr) throws Exception{
			String sql="update OrderInfo set addr=? where orderId=?";
			PreparedStatement ps=getConnection().prepareStatement(sql);
			ps.setString(1, addr);
			ps.setInt(2, orderId);
			ps.executeUpdate();
			getConnection().close();
		}
	

	//通过用户输入的菜品名称查看该菜品是否在可点赞的订单中
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

	
	//通过用户输入的订餐编号查询该订餐编号是否在可签收和取消的订单中
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
	
	//通过用户输入的订餐编号查询该订餐编号是否在可点赞的订单中
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

	//管理员查看所有订单
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
	
	//顾客查看订单
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

	//用户只要选择我要定餐，就自动生成一个订单编号 在OrderInfo表中
	public void creatOrder(String userName) throws Exception{
		String sql = "insert into OrderInfo(orderName) values(?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, userName);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//如果有相同的订单，则把数量累加
	public void orderNumAdd(int orderNum,String foodName,int orderId) throws Exception {
		String sql = "update Orderr set orderNum=orderNum+? where foodName=? and orderId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, orderNum);
		ps.setString(2,foodName);
		ps.setInt(3, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//从用户订单信息中找到本次订单的订单编号
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
	
	//将用户的订单信息插入到订单表中  在Orderr表中
	public void insertOrder1(int orderId,String foodName,int orderNum) throws Exception{
		String sql = "insert into Orderr values(?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.setString(2, foodName);
		ps.setInt(3, orderNum);
		ps.executeUpdate();
		getConnection().close();
	}
				
	//将用户的地址、送餐时间、订单金额以更新的方式插入到OrderInfo表中(通过订单编号)
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
