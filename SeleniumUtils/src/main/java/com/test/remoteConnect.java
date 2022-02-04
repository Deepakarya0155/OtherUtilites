package com.test;

import java.util.Optional;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class remoteConnect {
	public static void main(String[] args) throws InterruptedException {
		
		Optional<WebDriver> driver=SeleniumUtils.open(args[0],"webdriver.edge.driver" ,"msedgedriver.exe");
	
		
		
		
		SeleniumUtils.findElement(By.id("login"),driver.get()).get().sendKeys(args[1]);;
		SeleniumUtils.clickEvent(By.id("loginBtn"), driver.get(), 5);
		SeleniumUtils.findElement(By.id("passwd"),driver.get()).get().sendKeys(args[2]);;
		SeleniumUtils.clickEvent(By.id("loginBtn"), driver.get(), 5);
		
	}
}
