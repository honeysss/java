package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Food;

public class FoodDao extends Conn{
	//插入菜品
	public void insertFood(Food food) throws Exception{
		//数据操作
		String sql="insert into Food(foodName,price) values(?,?)";
		//获取sql语句
		PreparedStatement ps=getConnection().prepareStatement(sql);
		//赋值
		ps.setString(1,food.getFoodName());
		ps.setFloat(2, food.getPrice());
		//执行
		ps.executeUpdate();
		//关闭
		getConnection().close();
	}
	
	//通过菜品编号查询菜品名称和单价
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
			System.out.println("\t\t\t没有该菜品编号，请重新选购");
		}
		return food;	
	}

	//通过菜品编号查询菜品状态是否为1  如果为1 则可以定餐 
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
	
	
	//下架菜品
 	public void downFood(int foodId) throws Exception{
		String sql="update Food set state=0 where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//上架菜品
	public void reShelf(int foodId) throws Exception{
		String sql="update Food set state=1 where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, foodId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//改单价
	public void updateFood(int foodId,float price) throws Exception {
		String sql="update Food set price=? where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setFloat(1, price);
		ps.setInt(2, foodId);
		ps.executeUpdate();
		System.out.println("\t\t\t修改成功！");
		getConnection().close();
	}
	
	//查看菜品
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
	
	//通过管理员输入的菜品名称查看是否已经有此菜品
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
	
	//通过顾客输入的菜品编号查询得到的菜品名称和查询得出的订单编号查看此时Orderr表中有没有此人相同的订单
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
	
	//点赞1  通过菜品名称和订单编号点赞
	public void upFood1(String foodName,int orderId) throws Exception {
		String sql="update Food set upNum=upNum+1 from Food,Orderr where Food.foodName=? and orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setInt(2, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//点赞2  通过订单编号点赞
	public void upFood2(int orderId) throws Exception {
		String sql="update Food set upNum=upNum+1 from Food,Orderr where Food.foodName=Orderr.foodName and orderId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setInt(1, orderId);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//通过菜品编号查询该菜品编号是否在表内
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
	
	//通过菜品编号和菜品名称查询该菜品名称是否已经存在
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
	
	//修改菜品名称
	public void updateFoodName(String foodName,int foodId) throws Exception{
		String sql="update Food set foodName=? where foodId=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, foodName);
		ps.setInt(2, foodId);
		ps.executeUpdate();
		getConnection().close();
	}

}