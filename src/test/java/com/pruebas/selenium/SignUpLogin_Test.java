package com.pruebas.selenium;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUpLogin_Test {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
    	System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @Test
    public void testRegistroUsuario() throws Exception {
    	
    	//Código para generar un string aleatorio de 8 caracteres alfanuméricos (para usuario nuevo cada vez)
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(8);
        Random random = new Random();  
        for (int i = 0; i < 8; i++) {
            int indiceRandom = random.nextInt(caracteres.length());
            char caracterRandom = caracteres.charAt(indiceRandom);
            sb.append(caracterRandom);
        }
        
        //Usuario y contraseña que vamos a registrar y logar:
        String username=sb.toString();
        String password="masu123";
        System.out.println("El usuario generado es: " + username);
    	
		//Abrir el navegador, cargar la página de inicio de https://www.demoblaze.com y maximizarla.
    	System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());
    	assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),"https://www.demoblaze.com/index.html",driver.getCurrentUrl());
        driver.manage().window().maximize();
        
		//Hacer clic en el enlace "Sign up" para acceder al cuadro de registro.
        driver.findElement(By.id("signin2")).click();
        Thread.sleep(1000);    	
        
		//Completar el formulario de registro (username y password) con información válida.
        driver.findElement(By.xpath("//*[@id=\"sign-username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("input[id=\"sign-password\"]")).sendKeys(password);

		//Enviar el formulario de registro haciendo click en el botón “Sign up”.
    	driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);  
        
		//Pulsar en el botón “Aceptar” del pop-up con el mensaje de confirmación de registro exitoso.
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();   	
        
		//Verificar que el usuario sea redirigido a la página de inicio.
    	System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());	
    	assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),"https://www.demoblaze.com/index.html", driver.getCurrentUrl());
    	
		//Hacer click sobre el enlace “Log in”
    	driver.findElement(By.id("login2")).click();
        Thread.sleep(1000);   
        
		//Rellenar los campos de “username” y “password” con los datos del nuevo usuario.
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        
		//Hacer click sobre el botón “Log in”.
    	driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);    
        
		//Verificar que el inicio de sesión es exitoso, comprobando que aparece en la página de inicio el mensaje “Welcome” + el username.
    	System.out.println("Estás en la página Inicio: " + driver.getCurrentUrl());	
        assertEquals("NO estás en la página Inicio, estás en: " + driver.getCurrentUrl(),"https://www.demoblaze.com/index.html",driver.getCurrentUrl());
    	
    	WebElement welcomeLink = driver.findElement(By.id("nameofuser"));
    	System.out.println("El mensaje esperado es: Welcome " + username + ". El texto obtenido es: " + welcomeLink.getText());
    	assertEquals("Los mensajes no son iguales","Welcome " + username, welcomeLink.getText());     
    }

    @After
    public void tearDown() {
    	//Si comento el driver.quit() es para que no se cierre la ventana y poder observar lo que ha ocurrido.
        driver.quit();
    }
}
