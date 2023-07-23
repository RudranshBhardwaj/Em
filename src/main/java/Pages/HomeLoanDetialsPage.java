package Pages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class HomeLoanDetialsPage extends Base{

	
	

	 By calculators=By.xpath("//*[@id=\"menu-item-dropdown-2696\"]");
	 By homeLoanemi=By.xpath("//*[text()='Home Loan EMI Calculator'] ");
	 By homeValue=By.xpath("//*[@id=\"homeprice\"]");
	 By homeLoantenure=By.xpath("//*[@id=\"homeloanterm\"]");
//	 By loanCalculator=By.xpath("//a[@title='Loan Calculator']");
	 
		public void home() throws InterruptedException, IOException {
			
			Row row=null;
			
			File file=new File("C:\\EMIcalculator\\src\\test\\resources\\datas.xlsx");
			
			
			logger = report.createTest("Obtaining the tables datas and storing it in excel sheet.");
			try {
			
				driver.findElement(calculators).click();
				
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.findElement(homeLoanemi).click();
				
				driver.findElement(homeValue).clear();
				driver.findElement(homeValue).sendKeys("20,00,000");
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				
				driver.findElement(homeLoantenure).click();
				driver.findElement(homeLoantenure).clear();
				//driver.findElement(homeLoantenure).sendKeys("5");
				
				//Taking Screenshot of HomeDetails Page after inputing the value
				takeScreenshot("HomeLoanDetailsPage");			

	}
					catch(Exception e) {}
			
			
			if(file.exists()) {
				if(file.delete()) {
					//	System.out.println("deleted");
				}
			}
		List<WebElement> list=driver.findElements(By.xpath("//tr[@class='row no-margin yearlypaymentdetails']/td"));

		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("mysheet");
			for(int j=0,k=0,w=0;j<list.size();j++,k++)
			{
				
			
			if((k%7)==0||k==0)
			{
				
				row=sheet.createRow(w);
				System.out.println("");
				w++;
			}
			if(k>6) {
				k=0;
			}
			System.out.print(list.get(j).getText()+"\t");
			row.createCell(k).setCellValue(list.get(j).getText());
			
		}
	        FileOutputStream outputStream = new FileOutputStream(file);
	          wb.write(outputStream);
	           outputStream.close();
	           wb.close();
		
		}		
		
}
