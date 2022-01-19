package br.ce.wcaquino.estrategia1;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

//Reaproveitamento de casos de teste
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTesteWeb {

    private final ChromeDriver driver = new ChromeDriver();

    @Before
    public void login(){
        driver.get("https://seubarriga.wcaquino.me/");
        driver.findElement(By.id("email")).sendKeys("denisguimaraes2012@gmail.com");
        driver.findElement(By.id("senha")).sendKeys("@Dd050698");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    //Entrada: 0
    //Saída: M1
    @Test
    public void test_1_adicionarConta(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/addConta']")).click();
        driver.findElement(By.id("nome")).sendKeys("Registro 1");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Verifica se conta foi adicionada e verifica consulta do registro
        Assert.assertEquals(driver.findElement(By.xpath("//td")).getText(), "Registro 1");
    }

    //Entrada: M1
    //Saída: M1
    @Test
    public void test_2_consultarConta(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//td")).getText(), "Registro 1");
    }

    //Entrada: M1
    //Saída: M2
    @Test
    public void test_3_alterarConta(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-edit']")).click();
        driver.findElement(By.id("nome")).clear();
        driver.findElement(By.id("nome")).sendKeys("Registro 2");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//td")).getText(), "Registro 2");
    }

    //Entrada: M2
    //Saída: 0
    @Test
    public void test_4_excluirRegistro(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(), "Conta removida com sucesso!");
    }

    @After
    public void fimTeste(){
        driver.quit();
    }

}
