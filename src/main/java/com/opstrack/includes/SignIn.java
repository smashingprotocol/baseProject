package com.opstrack.includes;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.opstrack.engine.Config;
import com.opstrack.form.SetInputField;
import com.opstrack.request.ClickElement;
import com.opstrack.utility.StatusLog;
import com.opstrack.verify.VerifyDocumentURL;
import com.opstrack.verify.verifyXPath;

public class SignIn {

	public static Properties properties;
	static boolean login = true;
	
	public static void login(WebDriver driver, String email, String password) throws Exception{
		// TODO Auto-generated method stub
		FileReader reader = new FileReader("opstrack.properties");
		properties = new Properties();
		properties.load(reader);
		
		SetInputField.byXPath(driver,properties.getProperty("HOME_INPUT_EMAIL_XPATH"),email);
		SetInputField.byXPath(driver,properties.getProperty("HOME_INPUT_PASSWORD_XPATH"),password);
		ClickElement.byXPath(driver, properties.getProperty("HOME_BTN_LOGIN_XPATH"));
		
		boolean testStatus = verifyXPath.isfound(Config.driver, properties.getProperty("HEADER_INPUT_SEARCH_XPATH"));
		StatusLog.printlnPassedResultTrue(Config.driver,"[LOGIN] User able to login.",testStatus);
		
	}

	public static void logout(WebDriver driver) throws Exception{
		// TODO Auto-generated method stub
		FileReader reader = new FileReader("opstrack.properties");
		properties = new Properties();
		
		try {
			properties.load(reader);
			
			//Check first if user is logged in, click sign out.
			if (verifyXPath.isfound(driver,properties.getProperty("HEADER_LINK_USER_XPATH"))){
				
				ClickElement.byXPath(driver, properties.getProperty("HEADER_LINK_USER_XPATH"));
				ClickElement.byXPath(driver, properties.getProperty("HEADER_LINK_SIGNOUT_XPATH"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void storeSelect(WebDriver driver, String siteLogin) throws Exception {
		FileReader reader = new FileReader("opstrack.properties");
		properties = new Properties();
		properties.load(reader);
		
		Boolean testStatus = VerifyDocumentURL.containsText(driver, properties.getProperty("LOGIN_NAVPOINT_SITESELECTOR"));
		StatusLog.printlnPassedResultTrue(Config.driver,"[VERIFY] Site Selector URL", testStatus);
		
		if(siteLogin.equals("bd")){
			ClickElement.byXPath(driver, properties.getProperty("LOGIN_LINK_SITESELECT_BD_XPATH"));
		}
		
		if(siteLogin.equals("pcm")){
			ClickElement.byXPath(driver, properties.getProperty("LOGIN_LINK_SITESELECT_PCM_XPATH"));
		}
		
	}
	
}
