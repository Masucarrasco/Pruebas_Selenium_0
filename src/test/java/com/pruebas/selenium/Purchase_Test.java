package com.pruebas.selenium;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Purchase_Test {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
    	System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/index.html");
    }
    
    @Test
    public void testPurchase() throws Exception {
		//Configurar la espera para la aparición de elementos	
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		
    	//Datos del usuario y productos que vamos a necesitar:
        String name="Blabla";
        String country= "Spain";
        String city= "Madrid";
        String creditCard="1234 5678 9012 3456";
        String month="Junio";
        String year="2023";
        String precioString;
        int precioPhone, precioLaptop, precioMonitor, precioTotal;
    	
		//Abrir el navegador, cargar la página de inicio de https://www.demoblaze.com y maximizarla.
    	System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());
    	assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),"https://www.demoblaze.com/index.html",driver.getCurrentUrl());
        driver.manage().window().maximize();
        
		//Hacer click en el enlace “Phones”, para elegir un teléfono.
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones")));
        driver.findElement(By.linkText("Phones")).click();
        
		//Hacer click en el enlace de un teléfono, para ser derivado a los detalles de dicho producto.
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")));
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")).click();   
        
		//Comprobar y guardar el precio del teléfono para verificar, al final, que el precio total de compra es el correcto.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbodyid\"]/h3")));
        precioString= driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText().replace("$", "").replace(" *includes tax", "");
        precioPhone=Integer.parseInt(precioString);
    	System.out.println("El precio del phone es: " + precioPhone);  
        
		//Añadir el teléfono al cart pulsando en el botón “Add to Cart”.
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")));
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();  
        
		//Pulsar en el botón “Aceptar” del pop-up con el mensaje de confirmación de producto añadido correctamente.
    	wait.until(ExpectedConditions.alertIsPresent());
    	driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        
		//Volver a página de inicio pulsando sobre el enlace “Home”, y repetir el procedimiento con el resto de productos.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")).click();
       
		//Hacer click en el enlace “Laptops”, para elegir un ordenador portátil.
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Laptops")));
        driver.findElement(By.linkText("Laptops")).click();
        
		//Hacer click en el enlace de un laptop, para ser derivado a los detalles de dicho producto.
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("MacBook air")));
        driver.findElement(By.linkText("MacBook air")).click();
        
		//Comprobar y guardar el precio del laptop para verificar, al final, que el precio total de compra es el correcto.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbodyid\"]/h3")));
        precioString= driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText().replace("$", "").replace(" *includes tax", "");
        precioLaptop=Integer.parseInt(precioString);
        System.out.println("El precio del laptop es: " + precioLaptop);
        
		//Añadir el laptop al cart pulsando en el botón “Add to Cart”.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")));
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();   
        
		//Pulsar en el botón “Aceptar” del pop-up con el mensaje de confirmación de producto añadido correctamente.
    	wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        
		//Volver a página de inicio pulsando sobre el enlace “Home”, y repetir el procedimiento con el último producto.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")).click();
        
		//Hacer click en el enlace “Monitors”, para elegir un monitor.
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Monitors")));
        driver.findElement(By.linkText("Monitors")).click();
        
		//Hacer click en el enlace de un monitor, para ser derivado a los detalles de dicho producto.
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("ASUS Full HD")));
        driver.findElement(By.linkText("ASUS Full HD")).click();    
        
		//Comprobar y guardar el precio del monitor para verificar, al final, que el precio total de compra es el correcto.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbodyid\"]/h3")));
        precioString= driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText().replace("$", "").replace(" *includes tax", "");
        precioMonitor=Integer.parseInt(precioString);
        System.out.println("El precio del monitor es: " + precioMonitor);
        
		//Añadir el monitor al cart pulsando en el botón “Add to Cart”.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")));
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        
		//Pulsar en el botón “Aceptar” del pop-up con el mensaje de confirmación de producto añadido correctamente.
    	wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
        
		//Clickar sobre el enlace “Cart” para dirigirnos al carrito de compra.
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        driver.findElement(By.id("cartur")).click();
        
		//Comprobar que el precio final de compra corresponde a la suma del precio de los tres artículos añadidos.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalp")));
        precioTotal= Integer.parseInt(driver.findElement(By.id("totalp")).getText());
    	assertEquals("El precio total NO se corresponde con los artículos",(precioPhone+precioLaptop+precioMonitor), precioTotal); 
    	System.out.println("El precio Total es: " + precioTotal + " y la suma de los articulos es: " + (precioPhone+precioLaptop+precioMonitor));
    	
		//Pulsar el botón “Place Order”, para rellenar la orden de compra.
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")));
    	driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    	
		//Rellenamos la orden de compra (Name, Country, City, Credit Card, Month y Year).
    	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=name]")));
    	driver.findElement(By.cssSelector("input[id=name]")).sendKeys(name);
    	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=country]")));
        driver.findElement(By.cssSelector("input[id=country]")).sendKeys(country);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=city]")));
        driver.findElement(By.cssSelector("input[id=city]")).sendKeys(city);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=card]")));
        driver.findElement(By.cssSelector("input[id=card]")).sendKeys(creditCard); 
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=month]")));
        driver.findElement(By.cssSelector("input[id=month]")).sendKeys(month);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[id=year]")));
        driver.findElement(By.cssSelector("input[id=year]")).sendKeys(year);
        
		//Hacer click en “Purchase”.
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")));
        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
    	
		//Comprobar que aparece el mensaje de compra exitosa.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[10]/h2")));
    	assertEquals("NO HAY mensaje de Compra Exitosa", "Thank you for your purchase!", driver.findElement(By.xpath("/html/body/div[10]/h2")).getText());
    	System.out.println("Mensaje esperado: Thank you for your purchase! |Mensaje recibido: " + driver.findElement(By.xpath("/html/body/div[10]/h2")).getText());
	    			
		//Hacer click en el botón “ok” para finalizar el proceso.
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[10]/div[7]/div/button")));
    	driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click(); 
    }

    @After
    public void tearDown() {
    	//Si comento el driver.quit() es para que no se cierre la ventana y poder observar lo que ha ocurrido.
        driver.quit();
    }
}
