package com.reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class Car {
	private double price;
	private String colour;
	
	public Car(double price, String colour){
		this.price = price;
		this.colour = colour;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	@Override
	public String toString(){
		return colour +"car costs $"+price;
	}
	
	
	public static void main(String[] args) {
		Car car = new Car(0.1d,"xxx");
//		Reference<Car> reference = new WeakReference<Car>(car); 
		Reference<Car> reference = new SoftReference<Car>(car); 
//		Reference<Car> reference = new PhantomReference<Car>(car); 
//		Reference<Car> reference = new FinalReference<Car>(car,null); 
		
		while(true){
//			System.out.println("here is the strong reference 'car' "+car);
			if(reference.get()!=null){
				System.out.println("I am life....");
			}else{
				System.out.println("gc....");
				break;
			}
		}
	}
	
	/**
	 * soft reference和weak reference一样, 但被GC回收的时候需要多一个条件:
	 *  当系统内存不足时(GC是如何判定系统内存不足? 是否有参数可以配置这个threshold?), 
	 *  soft reference指向的object才会被回收. 正因为有这个特性, 
	 *  soft reference比weak reference更加适合做cache objects的reference.
	 *  因为它可以尽可能的retain cached objects, 减少重建他们所需的时间和消耗.
	 */
	
}