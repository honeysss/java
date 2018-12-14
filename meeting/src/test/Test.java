package test;

import java.lang.Character.Subset;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Car> cars = new ArrayList<Car>();
		Car car1 = new Car(1, "±¦Âí1", "pink", 6);
		Car car4 = new Car(4, "±¦Âí2", "pink", 6);
		Car car5 = new Car(5, "±¦Âí3", "pink", 6);
		Car car2 = new Car(2, "±¼³Û", "black", 4);
		Car car3 = new Car(3, "¼×¿Ç³æ", "white", 4);
		
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
//		for(Car i:cars) {
//			System.out.println(i);
//		}
		
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getBrand().contains("±¦Âí")) {
				System.out.println(cars.get(i).getCarId());
			}
		}

	}

}
