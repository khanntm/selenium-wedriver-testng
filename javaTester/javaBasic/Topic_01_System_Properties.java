package javaBasic;

public class Topic_01_System_Properties {

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		
		System.out.println(projectPath + "\\\\browserDrivers\\\\geckodriver.exe");

	}

}
