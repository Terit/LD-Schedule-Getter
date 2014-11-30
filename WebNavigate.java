package org.openqa.selenium.example;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebNavigate  {
    WebDriver driver = new FirefoxDriver();
    WebElement element;
    String username = "051062";
    String password = "LondonDrugs6";
    
    public void ldSchedule() {
        
        gotoPage(driver,"https://myld.ca/dana-na/auth/url_default/welcome.cgi",username,password);
        
        
            
        boolean finished = false;
		
		while(finished == false){
			
                    String title = setTitle(driver);

                    switch(title) {
			
                        case "London Drugs Secure Access Employee Portal":
                            //password = JOptionPane.showInputDialog("New Password");
                            getPassword();
                            gotoPage(driver,"https://myld.ca/dana-na/auth/url_default/welcome.cgi",username,password);
                            
                        case "London Drugs Secure Access Employee Portal - Confirmation":
				
                            try {            
                		driver.findElement(By.name("btnContinue")).click(); //Confirm new session
                            } catch (Exception e){
                		System.out.println("Continue Session Page Error: " + e);
                            }
                            //break;
            		
			case "Instant Virtual Extranet":
				
                            try {           
                		driver.findElement(By.cssSelector("a")).click();
                            } catch(Exception e) {
               			System.out.println("Password Change Page Error: " + e);
                            }
                            //break;
            		
                        case "London Drugs Secure Access Employee Portal - Home":
            	
                            try {                                       
				driver.get("https://myld.ca/psp/por88hme/,DanaInfo=psphome.ld.com,SSO=U+?cmd=login/");         
                            } catch(Exception e) {
				System.out.println("London Bridge Error: " + e);              
                            }
                            //break;
					
			case "PeopleSoft 8 Sign-in":
				
                            try {
				element = driver.findElement(By.name("userid")); //find Username field enter username
				element.sendKeys("051062");

				element = driver.findElement(By.name("pwd")); //find Password field enter password
				element.sendKeys("LondonDrugs4");

				driver.findElement(By.name("Submit")).click();

                            } catch(Exception e) {
				System.out.println("Peoplesoft Error: " + e);
                            }
                            //break;
					
			case "Employee-facing registry content":
				
                            try{
				driver.get("https://myld.ca/psp/por88hme/EMPLOYEE/EMPL/s/,DanaInfo=psphome.ld.com+WEBLIB_EPPSC.HOMEPAGE.FieldFormula.IScript_AppHP?scname=ADMN_KRONOS_CENTRAL");
                            }catch(Exception e){
				System.out.println("Employee-facing Error: "+ e);
                            }
                            //break;
					
			case "Kronos Scheduling":
				
                            try{
				driver.get("https://myld.ca/psp/por88hme/EMPLOYEE/HR881PRD/c/,DanaInfo=psphome.ld.com+ROLE_EMPLOYEE.LD_SFH.GBL");
                            }catch(Exception e){
				System.out.println("Kronos Error: " + e);
                            }
                            //break;
				
			case "View My Work Schedule":
                            finished = true;
                            try{

                                //int rowCount = driver.findElements(By.tagName("td")).size();
                                //int columnCount=driver.findElements(By.xpath("//table/tbody/tr/td")).size();
                                //System.out.println("col count " + columnCount);
                
                                /*WebElement table = driver.findElement(By.id("LD_EE_SCHED_VW$scroll$0"));
                                List <WebElement> tbody = table.findElements(By.tagName("tbody"));
                                List <WebElement> rows = tbody.get(0).findElements(By.tagName("tr"));
                                List <WebElement> cols = rows.get(0).findElements(By.tagName("td"));
                                List <WebElement> span = cols.get(0).findElements(By.tagName("span"));
                                System.out.println(span.get(0)); 
                                */
                               }catch(Exception e){
               
                               }
                            break;
                        default: 
                            finished = true;
                            break;
                    }
            }
            		
    }
    
    private void getPassword(){
        JPasswordField pass = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pass, "Enter New Password", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        
        if (okCxl == JOptionPane.OK_OPTION) {
            String localPass = new String(pass.getPassword());
            password = localPass;
            System.err.println("You Entered: " + localPass);
        }
    }
    
    public void tableTest() {
        
        driver.get("C:\\Users\\Andy\\Desktop\\New folder\\index.html");
        
        try {

            int rowCount = driver.findElements(By.tagName("td")).size();
            int columnCount=driver.findElements(By.xpath("//table/tbody/tr/td")).size();
            System.out.println("col count " + columnCount);

            WebElement table = driver.findElement(By.id("LD_EE_SCHED_VW$scroll$0"));
            List <WebElement> tbody = table.findElements(By.tagName("tbody"));
            List <WebElement> rows = tbody.get(0).findElements(By.tagName("tr"));
            List <WebElement> cols = rows.get(0).findElements(By.tagName("td"));
            List <WebElement> span = cols.get(0).findElements(By.tagName("span"));
            System.out.println("spans " + span.get(0)); 
            System.out.println("rows " + rows.size());
            System.out.println("colums " + cols.size());

       } catch(Exception e){

       }
        
       exit();
        
    }
    
    public void gotoPage(WebDriver driver,String site, String username, String password){
    	
        password = this.password;
        
    	driver.get(site);
    	
    	element = driver.findElement(By.name("username"));
    	element.sendKeys(username);
    	
    	element = driver.findElement(By.name("password"));
    	element.sendKeys(password);
    	
    	driver.findElement(By.name("btnSubmit")).click();
    	  	
    }  
    
    public void getTitle(WebDriver driver){
        System.out.println(driver.getTitle());
    }
    public String setTitle(WebDriver driver){
        String title = driver.getTitle();
        getTitle(driver);
        return title;
    }
    public void exit(){
        driver.quit();
    }
}