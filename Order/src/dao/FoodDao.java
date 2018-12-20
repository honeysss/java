package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Food;

public class FoodDao extends Conn{
	//�����Ʒ
	public void insertFood(Food food) throws Exception{
		//���ݲ���
		String sql="insert into Food(foodName,price) values(?,?)";
		//��ȡsql���
		PreparedStatement ps=getConnection().prepareStatement(sql);
		//��ֵ
		ps.setString(1,food.getFoodName());
		ps.setFloat(2, food.getPrice());
		//ִ��
		ps.executeUpdate();
		//�ر�
		getConnection().close();
	}
	
	//ͨ����Ʒ��Ų�ѯ��Ʒ���ƺ͵���
	public Food idName(int foodId) throws SQLException {
		String sql = "select foodName,price from Food where foodId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ResultSet set = ps.executeQuery();
		Food food = new Food();
		if(set.next()) {
			String foodName = set.getString(1);
			Float price = set.getFloat(2);
			food.setFoodName(foodName);
			food.setPrice(price);
		}else {
			System.out.println("\t\t\tû�иò�Ʒ��ţ�������ѡ��");
		}
		return food;	
	}

	//ͨ����Ʒ��Ų�ѯ��Ʒ״̬�Ƿ�Ϊ1  ���Ϊ1 ����Զ��� 
	public int idState(int foodId) throws SQLException {
		String sql = "select state from Food where foodId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ResultSet set = ps.executeQuery();
		int state = 2;
		if(set.next()) {
			state = set.getInt(1);
		}
		return state;
	}
	
	
	//�¼ܲ�Ʒ
 	public void downFood(int foodId) throws Exception{
		String sql="update Food set state=0 where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//�ϼܲ�Ʒ
	public void reShelf(int foodId) throws Exception{
		String sql="update Food set state=1 where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//�ĵ���
	public void updateFood(int foodId,float price) throws Exception {
		String sql="update Food set price=? where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setFloat(1, price);
		ps.setInt(2, foodId);
		ps.executeUpdate();
		System.out.println("\t\t\t�޸ĳɹ���");
		getConnection().close();
	}
	
	//�鿴��Ʒ
	public ArrayList<Food> selectFood() throws Exception {
		String sql="select * from Food";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ResultSet set=ps.executeQuery();
		ArrayList<Food> alist = new ArrayList<Food>();
		while(set.next()){
			int foodId = set.getInt(1);
			String foodName = set.getString(2);
			float price = set.getFloat(3);
			int upNum = set.getInt(4);
			int state = set.getInt(5);
			Food food = new Food(foodId, foodName, price, upNum, state);
			alist.add(food);
		}
		getConnection().close();
		return alist;
	}
	
	//ͨ������Ա����Ĳ�Ʒ���Ʋ鿴�Ƿ��Ѿ��д˲�Ʒ
	public int foodNameNum(String foodName) throws Exception{
		String sql = "select count(foodName) from Food where foodName=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ResultSet set = ps.executeQuery();
		int num = 0;
		if(set.next()){
			num = set.getInt(1);
		}
		getConnection().close();
		return num;
	}
	
	//ͨ���˿�����Ĳ�Ʒ��Ų�ѯ�õ��Ĳ�Ʒ���ƺͲ�ѯ�ó��Ķ�����Ų鿴��ʱOrderr������û�д�����ͬ�Ķ���
	public int foodIdFind(String foodName,int orderId) throws Exception {
		String sql = "select * from Orderr where foodName=? and orderId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setInt(2, orderId);
		ResultSet set = ps.executeQuery();
		int num = 0;
		if(set.next()) {
			num = 1;
		}
		getConnection().close();
		return num;
	}
	
	//����1  ͨ����Ʒ���ƺͶ�����ŵ���
	public void upFood1(String foodName,int orderId) throws Exception {
		String sql="update Food set upNum=upNum+1 from Food,Orderr where Food.foodName=? and orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setInt(2, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//����2  ͨ��������ŵ���
	public void upFood2(int orderId) throws Exception {
		String sql="update Food set upNum=upNum+1 from Food,Orderr where Food.foodName=Orderr.foodName and orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//ͨ����Ʒ��Ų�ѯ�ò�Ʒ����Ƿ��ڱ���
	public int idFoodId(int foodId) throws SQLException {
		String sql = "select * from Food where foodId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ResultSet set = ps.executeQuery();
		int exist = 0;
		if(set.next()) {
			exist = 1;
		}
		getConnection().close();
		return exist;
	}
	
	//ͨ����Ʒ��źͲ�Ʒ���Ʋ�ѯ�ò�Ʒ�����Ƿ��Ѿ�����
	public int nameFindName(String foodName,int foodId) throws SQLException {
		String sql = "select * from Food where foodName=? and foodId=?";
		PreparedStatement ps = getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setInt(2,foodId);
		ResultSet set = ps.executeQuery();
		int exist = 0;
		if(set.next()) {
			exist = 1;
		}
		getConnection().close();
		return exist;
	}
	
	//�޸Ĳ�Ʒ����
	public void updateFoodName(String foodName,int foodId) throws Exception{
		String sql="update Food set foodName=? where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setInt(2, foodId);
		ps.executeUpdate();
		getConnection().close();
	}

}