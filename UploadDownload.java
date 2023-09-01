package AssignmentTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class UploadDownload {
	public static void main(String[] args) {

		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Aakriti Sharma\\Desktop\\New folder\\chromedriver-win32\\chromedriver.exe");
		
		// Create a instance of the ChromeDriver
		WebDriver driver = new ChromeDriver();
		
		// Maximize the window
		driver.manage().window().maximize();

		// Navigate to the web page with attachments
		driver.get("https://the-internet.herokuapp.com/download");

		try {
			// Find all download links on the page
			List<WebElement> downloadLinks = driver.findElements(By.cssSelector(".example a"));

			// Loop through the links and download each file
			for (WebElement link : downloadLinks) {
				String href = link.getAttribute("href");
				String fileName = href.substring(href.lastIndexOf('/') + 1);

				// Check if the file is a JPG
				if (fileName.endsWith(".jpg")) {
					link.click();

					// Wait for the download to complete
					Thread.sleep(5000); 

					// Navigate to another webpage
					driver.get("https://the-internet.herokuapp.com/upload");

					// Locate the file input element and send the file path
					WebElement fileInput = driver.findElement(By.id("file-upload"));
					fileInput.sendKeys("C:\\Users\\Aakriti Sharma\\Downloads\\phoenix.jpg");

					// Click the Upload button
					driver.findElement(By.id("file-submit")).click();

					// Wait for the upload to complete
					Thread.sleep(5000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Close the WebDriver
			driver.quit();
		}
	}
}
