package Pages;

import org.openqa.selenium.By;

import Base.Base;

public class EMICalculatorPage extends Base{

	By carLoan =By.xpath("//a[text()='Car Loan']");
	By carLoanAmount=By.xpath("//*[@id=\"loanamount\"]");
	By interestRate=By.xpath("//*[@id=\"loaninterest\"]");
	By loanTenure=By.xpath("//*[@id=\"loanterm\"]");
	By year2023=By.xpath("//*[@id=\"year2023\"]");
	By firstMonth=By.xpath("//*[@id=\"monthyear2023\"]/td/div/table/tbody/tr[1]/td[1]");
	By principal=By.xpath("//*[@id='monthyear2023']/td/div/table/tbody/tr[1]/td[2]");
	By interest=By.xpath("//*[@id='monthyear2023']/td/div/table/tbody/tr[1]/td[3]");
//	By calculators=By.xpath("//*[@id=\"menu-item-dropdown-2696\"]");
//	By homeLoanemi=By.xpath("//*[text()='Home Loan EMI Calculator'] ");
	
	
	public void emi() throws InterruptedException {
		logger = report.createTest("Getting interest and principle amount for Car Loan.");
		try {
			driver.findElement(carLoan).click();
			//Removing the previous data
			driver.findElement(carLoanAmount).clear();
			driver.findElement(carLoanAmount).sendKeys("1500000");
			Thread.sleep(3000);
			
			driver.findElement(interestRate).clear();
			driver.findElement(interestRate).sendKeys(".5");
			Thread.sleep(3000);
			
			driver.findElement(loanTenure).clear();
			Thread.sleep(1000);
			takeScreenshot("EMICalculatorPage");

			driver.findElement(year2023).click();
			String interests=driver.findElement(interest).getText();
			String principals=driver.findElement(principal).getText();
			
			Thread.sleep(1000);
			System.out.println("The interest for the month is "+interests+" and principle amount is "+principals);
			Thread.sleep(5000);			
			reportPass("Interest and Principle amount are obtained.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
