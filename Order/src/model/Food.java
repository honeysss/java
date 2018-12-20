package model;

public class Food {
	private int foodId;
	private String foodName;
	private float price;
	private int upNum;
	private int state;
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price2) {
		this.price = price2;
	}
	public int getUpNum() {
		return upNum;
	}
	public void setUpNum(int upNum) {
		this.upNum = upNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Food(int foodId, String foodName, float price, int upNum, int state) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.price = price;
		this.upNum = upNum;
		this.state = state;
	}
	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "菜品编号：" + foodId + "  菜品名称：" + foodName + "  单价："+ price + "  点赞数：" + upNum + "  状态(0下架，1存在)=" + state;
	}

	
}
