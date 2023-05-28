package Script_test;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Script_test {
	// Les methodes : keywords
	WebDriver driver = new ChromeDriver();

	// *************************************Préconditions : Keyword Créergroupe******************************************************************************
	public void creer_un_groupe() throws IOException, InterruptedException 
	{
		FileInputStream reader = new FileInputStream("C:\\Users\\behra\\OneDrive\\Bureau\\POO\\ProjetScriptSeleniumJava\\src\\test\\java\\config_data\\config.properties");        
        Properties props = new Properties();
        props.load(reader);
		driver.findElement(By.xpath(props.getProperty("menu_outils"))).click();
		driver.findElement(By.xpath(props.getProperty("menue_groupe"))).click();
		driver.findElement(By.xpath(props.getProperty("button_parcourir"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(props.getProperty("icone_plus"))).click();
		driver.findElement(By.xpath(props.getProperty("identifiant_groupe"))).sendKeys("identifiant");
		Thread.sleep(2000);
		driver.findElement(By.xpath(props.getProperty("Nom_groupe"))).sendKeys("Nomgroupe");
		Thread.sleep(2000);
		driver.findElement(By.xpath(props.getProperty("button_creer"))).click();
		Thread.sleep(2000);
	}
	//*****************************************Keyword Supprimer_groupe********************************************
	public void supprimer_un_groupe() throws IOException, InterruptedException 
	{
		FileInputStream reader = new FileInputStream("C:\\Users\\behra\\OneDrive\\Bureau\\POO\\ProjetScriptSeleniumJava\\src\\test\\java\\config_data\\config.properties");        
        Properties props = new Properties();
        props.load(reader);
		driver.findElement(By.xpath(props.getProperty("menu_outils"))).click();
		driver.findElement(By.xpath(props.getProperty("menue_groupe"))).click();
		driver.findElement(By.xpath(props.getProperty("champs_recherche"))).sendKeys("Nomgroupe");
		Thread.sleep(3000);
		driver.findElement(By.xpath(props.getProperty("button_recherche"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(props.getProperty("icone_supprime"))).click();
		driver.findElement(By.xpath(props.getProperty("button_supprime"))).click();
		Thread.sleep(3000);
	}
	
	//*******************************************Criteres de succes : keyword validation ***********************************************************
	public void valider_groupe_esiste_pas() throws IOException, InterruptedException
	{
		FileInputStream reader = new FileInputStream("C:\\Users\\behra\\OneDrive\\Bureau\\POO\\ProjetScriptSeleniumJava\\src\\test\\java\\config_data\\config.properties");        
        Properties props = new Properties();
        props.load(reader);
		driver.findElement(By.xpath(props.getProperty("menu_outils"))).click();
		driver.findElement(By.xpath(props.getProperty("menue_groupe"))).click();
		driver.findElement(By.xpath(props.getProperty("champs_recherche"))).sendKeys("Nomgroupe");
		Thread.sleep(3000);
		driver.findElement(By.xpath(props.getProperty("button_recherche"))).click();
		WebElement element = driver.findElement(By.xpath("//div[text()='Rechercher pour voir les groupes.']"));
		assert element.getText().equals("Rechercher pour voir les groupes.");
		
	}
	// *******************************************Cas de test : Keyword Supprimer groupe*****************************************************************

	@Test
	public void Supprimer_un_groupe() throws IOException, InterruptedException 
	{
		creer_un_groupe();
		supprimer_un_groupe();
		valider_groupe_esiste_pas();
	}

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver.manage().window().maximize();
		driver.get("http://localhost:8087/share/page/");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("6208240");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("6208240");
		driver.findElement(By.xpath("//button[@type='button']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
