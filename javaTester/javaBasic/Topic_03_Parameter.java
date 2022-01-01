package javaBasic;

public class Topic_03_Parameter {
	// Parameter toàn cuc dung chung cho tat ca cac ham trong class nay
	//String carNumber = "Honda";
	//String carNumber = "50";

	public static void main(String[] args) {
		Topic_03_Parameter topic = new Topic_03_Parameter();
		topic.showCarName();
		topic.showCarName("Honda");
		topic.showCarName("Huyndai");
		topic.showCarName("Toyota");
		
		topic.showCarName("Honda", "6");
		topic.showCarName("Toyota", "100");

	}
	
	public void showCarName() {
		System.out.println("No para");
		
	}
	
	public void showCarName(String carName)
	{
		// Parameter cuc bo co mau xam xam
		//this.carNumber = carName;
		//System.out.println("One para:" + carName);
	}
	
	public void showCarName(String carName, String carNumber)
	{
		System.out.println("Two para:"+ carName + "-" + carNumber);
	}
	
	
}
