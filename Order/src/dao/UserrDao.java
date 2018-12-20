package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Userr;

public class UserrDao extends Conn {
	//注册
	public void sign(Userr userr) throws Exception{
		//数据操作
		String sql="insert into Userr(userName,psw,tel) values(?,?,?)";
		//获取sql语句 conn
		PreparedStatement ps=getConnection().prepareStatement(sql);
		//赋值
		ps.setString(1, userr.getUserrName());
		ps.setString(2, userr.getPsw());
		ps.setString(3, userr.getTel());
		//执行
		ps.executeUpdate();
		//关闭
		getConnection().close();
	}
	//管理员或用户修改密码
	public void updatePsw(String psw,String userName) throws Exception {
		String sql="update Userr set psw=? where userName=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, psw);
		ps.setString(2, userName);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//管理员或用户修改手机号
	public void updateTel(String tel,String userName) throws Exception {
		String sql="update Userr set tel=? where userName=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, tel);
		ps.setString(2, userName);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//登陆时判断是否有此人信息
	public Userr select(String userrName,String psw) throws Exception {
		String sql="select * from Userr where userName=? and psw=?";
		Connection conn = getConnection();
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1,userrName);
		ps.setString(2,psw);
		ResultSet set = ps.executeQuery();
		Userr userr=new Userr();
		userr=null;
		boolean next=set.next();
		if(next){
			String userrName1 = set.getString(1);
			String psw1 = set.getString(2);
			String tel = set.getString(3);
			int identify = set.getInt(4);
			userr=new Userr(userrName1, psw1, tel, identify);
		}
		conn.close();
		return userr;
	}


}
