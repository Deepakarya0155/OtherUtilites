package com.test;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumUtils {
	
	
	public static Optional<WebDriver> open(String url,String driverProp,String driverpath) {
		WebDriver driver=null;
		
		System.setProperty(driverProp, driverpath);
		
		if(driverProp.equals("webdriver.chrome.driver")) {
			driver=new ChromeDriver();
			
		}else if(driverProp.equals("webdriver.edge.driver")) {
			driver=new EdgeDriver();
		}
		// scope of addition
		
		if(driver!=null) {
			driver.get(url);
		}
		
		return Optional.of(driver);
	}
	
	
	public static  Optional<WebElement> clickEvent(By by,WebDriver driver,Integer retry) throws InterruptedException{
		WebElement element=null;
		Boolean flag=true;
		Integer temp=1;
		while(flag) {
			try {
				element = driver.findElement(by);
				element.click();
				flag=false;
			}catch(Exception e) {
				System.out.println("Exception "+ by);
				Thread.sleep(1000*temp);
			}
			temp++;
			if(temp==retry) {
				System.out.println("Unable to find element after lots of ");
				flag=false;
			}
		}
		
		return Optional.of(element);
	}
	
	public static  Optional<WebElement> findElement(By by,WebDriver driver,Integer retry) throws InterruptedException{
		WebElement element=null;
		Boolean flag=true;
		Integer temp=1;
		while(flag) {
			try {
				element = driver.findElement(by);
				if(element==null) {
					throw new Exception();
				}
				flag=false;
			}catch(Exception e) {
				System.out.println("Exception "+ by);
				Thread.sleep(1000*temp);
			}
			temp++;
			if(temp==retry) {
				System.out.println("Unable to find element after lots of ");
				flag=false;
			}
		}
		
		return Optional.of(element);
	}
	
	public static  Optional<List<WebElement>> findElements(By by,WebDriver driver,Integer retry) throws InterruptedException{
		List<WebElement> elements=null;
		Boolean flag=true;
		Integer temp=1;
		while(flag) {
			try {
				elements = driver.findElements(by);
				if(elements==null || elements.size()==0) {
					throw new Exception();
				}
				flag=false;
			}catch(Exception e) {
				System.out.println("Exception "+ by);
				Thread.sleep(1000*temp);
			}
			temp++;
			if(temp==retry) {
				System.out.println("Unable to find element after lots of ");
				flag=false;
			}
		}
		
		return Optional.of(elements);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
//		document.getElementsByClassName("ytp-ad-skip-button ytp-button")[0].click()
		
//		ytp-play-button ytp-button
//		ytp-ad-skip-button ytp-button
		
//		Optional<WebDriver> driver=open("https://www.youtube.com/watch?v=6MOsfVTq_E0&list=RDGMEM916WJxafRUGgOvd6dVJkeQ&start_radio=1&rv=J7R9uROihVIs","webdriver.edge.driver","msedgedriver.exe");
		Optional<WebDriver> driver=open("https://www.youtube.com/watch?v=6MOsfVTq_E0&list=RDGMEM916WJxafRUGgOvd6dVJkeQ&start_radio=1&rv=J7R9uROihVIs",args[0],args[1]);
		
		if(!driver.isPresent()) {
			System.out.println("driver is null");
			return;
		}
		
		Optional<List<WebElement>> playButton=findElements(By.cssSelector("#movie_player > div.ytp-chrome-bottom > div.ytp-chrome-controls > div.ytp-left-controls > button"), driver.get(), 5);
		
		playButton.get().get(0).click();
		
		
		while(true) {
//				
			try {
//				Optional<List<WebElement>> skipButton=findElements(By.cssSelector("#skip-button\\:p > span > button"), driver.get(), 5);
//				playButton.get().get(0).click();
				
				
				JavascriptExecutor executer=(JavascriptExecutor) driver.get();
				executer.executeScript("document.getElementsByClassName(\"ytp-ad-skip-button ytp-button\")[0].click()");
				System.out.println("button clicked");
			}catch(Exception e) {
				System.out.println("Exception skips ");
				Thread.sleep(1000*3);
			}
		}
		
//		
//		Thread.sleep(1000*10);
//		
//		driver.get().close();
		
	}
}
