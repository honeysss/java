package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Userr;

public class UserrDao extends Conn {
	//ע��
	public void sign(Userr userr) throws Exception{
		//���ݲ���
		String sql="insert into Userr(userName,psw,tel) values(?,?,?)";
		//��ȡsql��� conn
		PreparedStatement ps=getConnection().prepareStatement(sql);
		//��ֵ
		ps.setString(1, userr.getUserrName());
		ps.setString(2, userr.getPsw());
		ps.setString(3, userr.getTel());
		//ִ��
		ps.executeUpdate();
		//�ر�
		getConnection().close();
	}
	//����Ա���û��޸�����
	public void updatePsw(String psw,String userName) throws Exception {
		String sql="update Userr set psw=? where userName=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, psw);
		ps.setString(2, userName);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//����Ա���û��޸��ֻ���
	public void updateTel(String tel,String userName) throws Exception {
		String sql="update Userr set tel=? where userName=?";
		PreparedStatement ps=getConnection().prepareStatement(sql);
		ps.setString(1, tel);
		ps.setString(2, userName);
		ps.executeUpdate();
		getConnection().close();
	}
	
	//��½ʱ�ж��Ƿ��д�����Ϣ
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
