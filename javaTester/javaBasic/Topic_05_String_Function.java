package javaBasic;

import org.testng.Assert;

public class Topic_05_String_Function {
	
	public static void main (String[] args) {
		/* Nối chuỗi:
		String itemText = "\n      First Option\n        ";
		System.out.println(itemText);
		System.out.println(itemText.trim());
		Assert.assertEquals(itemText.trim(), "First Option");
		*/
		
		// Tách chuỗi 
		String username = "adim";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		System.out.println(url);
		
		String[] urlValues = url.split("//");
		//http: = urlValues[0]
		//the-internet.herokuapp.com/basic_auth = urlValues[1]
		url = urlValues[0] + "//" + username + ":" + password + "@" + urlValues[1];
		System.out.println(url);				
		
	}

}
