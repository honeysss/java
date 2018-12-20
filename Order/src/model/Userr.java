package model;
public class Userr {
	private String userrName;
	private String psw;
	private String tel; 
	private int identify;
	public String getUserrName() {
		return userrName;
	}
	public void setUserrName(String userrName) {
		this.userrName = userrName;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getIdentify() {
		return identify;
	}
	public void setIdentify(int identify) {
		this.identify = identify;
	}
	public Userr(String userrName, String psw, String tel, int identify) {
		super();
		this.userrName = userrName;
		this.psw = psw;
		this.tel = tel;
		this.identify = identify;
	}
	public Userr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Userr [userrName=" + userrName + ", psw=" + psw + ", tel="
				+ tel + ", identify=" + identify + "]";
	}
	
	
	
}
