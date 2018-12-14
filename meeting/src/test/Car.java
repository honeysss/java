package test;

public class Car {
	private int carId;
	private String brand;
	private String color;
	private int volume;
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Car(int carId, String brand, String color, int volume) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.color = color;
		this.volume = volume;
	}
	public Car() {
		super();
	}
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", brand=" + brand + ", color=" + color
				+ ", volume=" + volume + "]";
	}
	
}
