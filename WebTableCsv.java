package AssignmentTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WebTableCsv {
    
	public static void main(String[] args) {
		
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aakriti Sharma\\Desktop\\New folder\\chromedriver-win32\\chromedriver.exe");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the web page
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        try {
            
            // Extract data from the table
            WebElement table = driver.findElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]"));
            
         // Table headers
            String[] headers = {"Lorem","Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action"};

         // Print table headers
            for (String header : headers) {
                System.out.print(header + "\t");
            }
            
                
            
            List<WebElement> rows = table.findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    System.out.print(cell.getText() + "\t");
                }
                System.out.println();
            }

            // Generate a CSV file name with a timestamp
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH-mm-ss");
            String timestamp = dateFormat.format(new Date());
            String csvFileName = "C:\\HOME\\briq\\webtable_" + timestamp + ".csv";

            // Create a FileWriter for the CSV file
            FileWriter csvWriter = new FileWriter(csvFileName);
            
         // Write headers to the CSV file
            for (int i = 0; i < headers.length; i++) {
                csvWriter.append(headers[i]);
                if (i < headers.length - 1) {
                    csvWriter.append(",");
                } else {
                    csvWriter.append("n");
                }
            }

            // Write data to the CSV file
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (int i = 0; i < cells.size(); i++) {
                    csvWriter.append(cells.get(i).getText());
                    if (i < cells.size() - 1) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            // Close the CSV file
            csvWriter.flush();
            csvWriter.close();

            System.out.println("CSV file created: " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver session
            driver.quit();
        }
    }
}