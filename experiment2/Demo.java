package com.experiment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
	public static void main(String[] args) throws IOException {
		String filename=System.getProperty("user.dir")+"/Selenium+Lab2020.xlsx";
		String driverPath = System.getProperty("user.dir") +"/src/resources/driver/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		WebDriver driver=new ChromeDriver();
	
        //二、定义要访问的网址
        String baseUrl = "http://103.120.226.190/selenium-demo/git-repo";
        driver.get(baseUrl);

		InputStream is =null;
		Workbook workbook=null;
		is =new FileInputStream(filename);
		if(filename.endsWith(".xlsx")) {
			workbook=new XSSFWorkbook(is);
		}else if (filename.endsWith(".xls")) {
			workbook=new HSSFWorkbook(is);
		}
		is.close();
		//读取sheet表的内容
		Sheet sheet=workbook.getSheetAt(0);
		//获取行
		Iterator<Row> rows= sheet.rowIterator();
		
		Row row;
		Cell cell;
		int row_max=20;
		String user_name=null;
		String pass_word=null;
		
		
		for(int i=0;i<row_max;i++) {
	        WebElement username=driver.findElement(By.name("user_number"));
			WebElement password=driver.findElement(By.name("password"));
			WebElement btn=driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[3]/input"));
			if(rows.hasNext()) {
				row=rows.next();
				Iterator<Cell> cells=row.cellIterator();
				if(cells.hasNext()) {
					cell=cells.next();
					user_name=cell.getStringCellValue();
					cell=cells.next();
					pass_word=cell.getStringCellValue();
				}
			}
			username.sendKeys(user_name);
			password.sendKeys(pass_word);
			btn.click();
			WebElement result=driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code"));
			if(result.getText().equals(pass_word)) {
				System.out.println("匹配成功");
			}
		}
		driver.close();
	}
}
