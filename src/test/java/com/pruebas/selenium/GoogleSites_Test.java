package com.pruebas.selenium;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSites_Test {
    private WebDriver driver;

	@Before
	public void setUp() throws Exception {
    	System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://sites.google.com/view/web-para-hacer-pruebas/inicio"); 
	}

	@Test
	public void test() throws InterruptedException {
		//Configurar la espera para la aparición de elementos	
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));

    	//Localizadores urls y elementos: con diferentes métodos de búsqueda
    	String pageInicioUrl = "https://sites.google.com/view/web-para-hacer-pruebas/inicio";
    	String pageContactoUrl = "https://sites.google.com/view/web-para-hacer-pruebas/contacto";	
    	String pageConocenosUrl = "https://sites.google.com/view/web-para-hacer-pruebas/quienes-somos";
    	String pageRegistroUrl= "https://sites.google.com/view/web-para-hacer-pruebas/registro";
    	By btnContactoLocator = By.xpath("//*[@id=\"h.7cb4eee316f09e37_0\"]/div/div/div/div/a/div[1]/p");
    	By btnInicioLocator = By.cssSelector("a[aria-label='Volver a INICIO']");
    	By btnConocenosLocator = By.id("h.7cb4eee316f09e37_14");
    	By btnRegistrateLocator= By.id("h.7cb4eee316f09e37_10");
    	By nombreLocator=By.cssSelector("input[aria-labelledby='i1']");
    	By apellidosLocator=By.cssSelector("input[aria-labelledby='i5']");
    	By correoLocator=By.cssSelector("input[aria-labelledby='i9']");
    	By btnEnviar=By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[3]/div[1]/div[1]/div/span/span");
    	By btnEntendido=By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div/div[3]/div/div[2]/div");
    	By registroExitosoLocator=By.xpath("/html/body/div[1]/div/div/div[1]/div[3]");
    	
		//Abrir el navegador, cargar la página de inicio de https://sites.google.com/view/web-para-hacer-pruebas/inicio y maximizarla.
    	System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());
    	assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),pageInicioUrl,driver.getCurrentUrl());
        driver.manage().window().maximize();   
        
		//Hacer clic en el botón “Contacta con nosotros”.
		wait.until(ExpectedConditions.elementToBeClickable(btnContactoLocator));
    	driver.findElement(btnContactoLocator).click();
    	
	    // Cambiar el control a la nueva ventana o pestaña y cerrar la antigua.
    	driver.close();
    	driver.switchTo().window(driver.getWindowHandles().iterator().next()); 	
        
		//Comprobar que se abre la página “Contacto”.
    	assertEquals("NO estás en la página Contacto, estás en: " + driver.getCurrentUrl(),pageContactoUrl,driver.getCurrentUrl());
    	System.out.println("Estás en la página Contacto: " + driver.getCurrentUrl());   	
    	
		//Pulsar el botón “Volver”, para volver a “Inicio” cerrando la ventana/pestaña antigua y cambiando el control a la nueva
		wait.until(ExpectedConditions.elementToBeClickable(btnInicioLocator));
    	driver.findElement(btnInicioLocator).click();
    	driver.close();
    	driver.switchTo().window(driver.getWindowHandles().iterator().next());

		//Comprobar que se abre la página “Inicio”.
		assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),pageInicioUrl,driver.getCurrentUrl());
		System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());
	
		//Hacer clic en el botón “Conoce más acerca de nosotros”, cerrando la ventana/pestaña antigua y cambiando el control a la nueva.
		wait.until(ExpectedConditions.elementToBeClickable(btnConocenosLocator));		
		driver.findElement(btnConocenosLocator).click();
    	driver.close();
    	driver.switchTo().window(driver.getWindowHandles().iterator().next()); 
    	
		//Comprobar que se abre la página “Quienes somos”.
		assertEquals("NO estás en la página Quienes somos estás en: " + driver.getCurrentUrl(),pageConocenosUrl,driver.getCurrentUrl());
		System.out.println("Estás en la página Quienes somos: " + driver.getCurrentUrl());
		
		//Pulsar el botón “Volver”, para volver a “Inicio”, cerrando la ventana/pestaña antigua y cambiando el control a la nueva.
		wait.until(ExpectedConditions.elementToBeClickable(btnInicioLocator));
		driver.findElement(btnInicioLocator).click();
    	driver.close();
    	driver.switchTo().window(driver.getWindowHandles().iterator().next()); 
    	
		//Comprobar que se abre de nuevo la página “Inicio”.
		assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),pageInicioUrl,driver.getCurrentUrl());
		System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());
    	
		//Hacer clic en el botón “Regístrate”, cerrando la ventana/pestaña antigua y cambiando el control a la nueva.
		wait.until(ExpectedConditions.elementToBeClickable(btnRegistrateLocator)); 
		driver.findElement(btnRegistrateLocator).click();
    	driver.close();
    	driver.switchTo().window(driver.getWindowHandles().iterator().next());	 
    	
		//Comprobar que se abre la página “Registro”.
		assertEquals("NO estás en la página Registro, estás en: " + driver.getCurrentUrl(),pageRegistroUrl,driver.getCurrentUrl());
		System.out.println("Estás en la página Registro: " + driver.getCurrentUrl());
		
		//Esperar hasta que el mensaje de gestión de cookies esté activo
		wait.until(ExpectedConditions.elementToBeClickable(btnEntendido));

		//Cambiar el foco al frame del formulario y rellenar los campos del mismo
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.elementToBeClickable(nombreLocator));
		driver.findElement(nombreLocator).sendKeys("Blabla");
		wait.until(ExpectedConditions.elementToBeClickable(apellidosLocator));
		driver.findElement(apellidosLocator).sendKeys("Bla Bla");
		wait.until(ExpectedConditions.elementToBeClickable(correoLocator));
		driver.findElement(correoLocator).sendKeys("blabla@blabla.com");
		
		//Hacer click en el botón Enviar
		wait.until(ExpectedConditions.elementToBeClickable(btnEnviar));
		driver.findElement(btnEnviar).click();
		
		//Comprobar que el usuario ha sido inscrito correctamente comprobando que se visualiza el mensaje de registro exitoso.
		wait.until(ExpectedConditions.visibilityOfElementLocated(registroExitosoLocator));
		System.out.println("El mensaje devuelto es: " + driver.findElement(registroExitosoLocator).getText());			
		assertEquals("Mensaje de registro exitoso INCORRECTO","¡Registrado con éxito!",driver.findElement(registroExitosoLocator).getText());
	
//		//conocer el elemento que está activo
//		WebElement elementoActivo = driver.switchTo().activeElement();
//		String tagName = elementoActivo.getTagName();
//		String text = elementoActivo.getText();
//		System.out.println("Elemento activo - Etiqueta: " + tagName + ", Texto: " + text);		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

