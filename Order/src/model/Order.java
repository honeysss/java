package model;

public class Order {
	private int orderId;
	private String foodName;
	private int orderNum;
	private String orderName;
	private String seTime;
	private int seState=0;
	private String addr;
	private int cancle=0;
	private float totalPrice;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getSeTime() {
		return seTime;
	}
	public void setSeTime(String seTime) {
		this.seTime = seTime;
	}
	public int getSeState() {
		return seState;
	}
	public void setSeState(int seState) {
		this.seState = seState;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getCancle() {
		return cancle;
	}
	public void setCancle(int cancle) {
		this.cancle = cancle;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Order(int orderId, String foodName, int orderNum, String orderName, String seTime, int seState, String addr,
			int cancle, float totalPrice) {
		super();
		this.orderId = orderId;
		this.foodName = foodName;
		this.orderNum = orderNum;
		this.orderName = orderName;
		this.seTime = seTime;
		this.seState = seState;
		this.addr = addr;
		this.cancle = cancle;
		this.totalPrice = totalPrice;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "订单编号=" + orderId + ", 菜品名称=" + foodName + ", 订餐数量=" + orderNum + ", 订餐人名称="
				+ orderName + ", 送餐时间=" + seTime + ", 送餐状态=" + seState + ", 地址=" + addr + ", 是否取消=" + cancle
				+ ", 订单金额=" + totalPrice;
	}
	
	
	
}
