package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Base.Base;

public class LoanCalculatorPage extends Base{

		By calculators=By.xpath("//*[@id=\"menu-item-dropdown-2696\"]");
		By loanAmount = By.xpath("//a[text()='Loan Amount ']");
		By beforeamt = By.xpath("//*[@id='loansummary-totalamount']/p");
		By slider =By.xpath("//*[@id='loaninterestslider']");
		By slide2 =By.xpath("//*[@id='loantermslider']/span");
		By slider1 =By.xpath("//*[@id='loanamountslider']"); 
		By month =By.xpath("//label[text()='Mo ']");
		By year =By.xpath("//label[text()='Yr ']");
		
		By scale = By.xpath("//*[@id='loantermsteps']/span/span");
		By loanten = By.xpath("//a[text()='Loan Tenure ']");
		By ten = By.xpath("//*[@id='loansummary-tenure']/p/span");
		
		By homeLoanemi=By.xpath("//*[text()='Home Loan EMI Calculator'] ");
		By loanCalculator=By.xpath("//a[@title='Loan Calculator']");
		
		 //For printing the values of scroll bar in EMI calculator page in Loan Calculator 
		public void scale() {
			List<WebElement> befscale = driver.findElements(scale);
			for (int i = 0; i < befscale.size(); i++) {
				befscale.get(i).getText();
				System.out.print(befscale.get(i).getText()+" ");
			}
		}
		public void loan() throws InterruptedException {
			logger = report.createTest("Check all the fields and slider and check the amount change.");
			try {
				driver.findElement(calculators).click();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.findElement(loanCalculator).click();
				
				String beforeamount=driver.findElement(beforeamt).getText();
				System.out.println();
				System.out.println("*********EMI Calculator*********");
				System.out.println("The amount before scrolling the Loan Amount scale is: "+beforeamount);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				//Changing the value in scroll bar and print the changes in amount before and after scrolling
				WebElement slide=driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]/span"));
				Actions act = new Actions(driver);
				
				act.dragAndDropBy(slide, 40, 54).build().perform();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				//Taking screenshot of Loan EMI in Loan Calculator Page
				takeScreenshot("LoanEMI");
				
				WebElement afteramountwebelement = driver.findElement(beforeamt);
				afteramountwebelement.click();
				String afteramount = afteramountwebelement.getText();
				System.out.println("The amount after scrolling the Loan Amount scale is: "+afteramount);
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,300)", "");
				
				//Printing the values in Year scale in EMI Calculator in Loan Page
				System.out.println("Year Scale:");
				scale();
				wait(20,month);
				WebElement month=driver.findElement(By.xpath("//*[@id='ltermwrapper']/div[1]/div/div/div/div/div/label[2]"));
				month.click();
				
				//Printing the values in Month scale in EMI Calculator in Loan Page
				System.out.println(" ");
				System.out.println("Month Scale:");
				scale();
				System.out.println(" ");
				
				
				System.out.println("*****Loan Amount Calculator*****");
				act.moveToElement(driver.findElement(loanAmount)).click().perform();
				
				//Printing the values in Year scale in Loan Calculator in Loan Page
				System.out.println("Month Scale:");
				scale();			
				wait(20,year);
				act.moveToElement(driver.findElement(year)).click().perform();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//Taking screenshot of changing EMI in Loan Calculator Page
				takeScreenshot("EMIchange");
				//Printing the values in Month scale in EMI Calculator in Loan Page
				System.out.println(" ");
				System.out.println("Year Scale:");
				scale();
				System.out.println(" ");
				
				System.out.println("*****Loan Tenure Calculator*****");
				act.moveToElement(driver.findElement(loanten)).click().perform();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				//Printing Loan Tenure before and after moving the scroll bar for Loan Amount
				System.out.println("Loan Tenure Before Scrolling the scale: "+driver.findElement(ten).getText());
				WebElement sliderXpath1 = driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]/span"));
				act.dragAndDropBy(sliderXpath1, 40, 14).perform();
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.findElement(ten).click();
				//Taking screenshot of Loan Tenure Page in Loan Calculator
				takeScreenshot("LoanTenure");
				System.out.println("Loan Tenure After Scrolling the scale: "+driver.findElement(ten).getText());			
				reportPass("Loan Calcutaor fields are checked and amount changed is obtained.");
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			}catch(Exception e) {
			e.printStackTrace();
		}}
}
