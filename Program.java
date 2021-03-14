package kim.halme.vrliput;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Program{
	
	private MainGUI guiMain;
	
	private String place;
	private String destination;
	private String time;
	private String date;
	
	private boolean selectTrain;
			
	private WebDriver driver;
	private WebElement element;
	
	@SuppressWarnings("deprecation")
	public Program() {
		
		System.setProperty("webdriver.opera.driver", "C:/Program Files/Java/Selenium/Opera driver/operadriver_win64/operadriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:/Program Files/Opera/53.0.2907.68/opera.exe");        

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new OperaDriver(capabilities);
	}
	
	public void runProgram(){

		
		driver.get("https://www.vr.fi/cs/vr/fi/etusivu");
		
		element = driver.findElement(By.id("tabs1_station"));
		element.sendKeys(place);
		
		element = driver.findElement(By.id("tabs1_stationdestination"));
		element.sendKeys(destination);
		
		element = driver.findElement(By.id("tabs1_startTime00"));
		element.clear();
		element.sendKeys(time);
		
		element = driver.findElement(By.id("tabs1_startDate"));
		element.clear();
		element.sendKeys(date);
		
		element = driver.findElement(By.id("tabs1_submitbutton1"));
		element.click();			
		
		if(selectTrain) {
			continueProgram();
		}else {
			guiMain.drawFrame2();
		}
	}
	public void continueProgram() {
		
		if(selectTrain)
			driver.findElements(By.name("outwardJourneyInput")).get(1).click();
		
		
		for(int i = 0; i < 10; i++) {
			
			boolean exists = driver.findElements(By.id("moveToServices_0_" + Integer.toString(i))).size() > 0;
			
			if(exists) {
				element = driver.findElement(By.id("moveToServices_0_" + Integer.toString(i)));
				if(element.isDisplayed() == true) {
					element.click();
					break;
				}
					
			}	
				
		}
		
		guiMain.drawFrame3();
	}
	
	public void continueProgram2() {
		driver.findElement(By.id("pricesContinueBtn")).click();
		
		driver.findElement(By.id("all2OneEmail0_0")).sendKeys("sähköposti");
		
		driver.findElement(By.id("all2OnePhone0_0")).sendKeys("puhelinnumero");
		
		driver.findElement(By.id("uniqueIdentifierId")).sendKeys("salasana");
		
		driver.findElement(By.id("approveTerms")).click();
		
		driver.findElement(By.id("NORDEA_link")).click();
		
		driver.findElement(By.name("A02Y_USERID")).sendKeys("pankkitunnus");
		
		driver.findElement(By.name("OK")).click();
	}
	
	
	public void setGUI(MainGUI guiMain) {
		this.guiMain = guiMain;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setSelectTrain(boolean selectTrain) {
		this.selectTrain = selectTrain;
	}

}
