package br.ce.wcaquino.estrategia2;

import com.github.javafaker.Faker;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

//Reaproveitamento de casos de teste
public class ContaTesteWeb {

    private final ChromeDriver driver = new ChromeDriver();
    private final Faker faker = new Faker();

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
    public void adicionarConta(){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/addConta']")).click();
        String contaCriada = faker.harryPotter().character();
        driver.findElement(By.id("nome")).sendKeys(contaCriada);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(), "Conta adicionada com sucesso!");
        //Opcional
        //apagaRegistro(driver.findElement(By.xpath("//tr[contains(., '"+contaCriada+"')]/td")).getText());
    }

    //Entrada: M2
    //Saída: M2
    @Test
    public void consultarConta(){
        String conta = inserirConta();
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr[contains(., '"+conta+"')]/td")).getText(), conta);
        //Opcional
        //apagaRegistro(conta);
    }

    //Entrada: M3
    //Saída: M3'
    @Test
    public void alterarConta(){
        String conta = inserirConta();
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        driver.findElement(By.xpath("//tr[contains(., '"+conta+"')]/td/a/span[@class='glyphicon glyphicon-edit']")).click();
        driver.findElement(By.id("nome")).sendKeys(" Alterado");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tr[contains(., '"+conta+"')]/td")).getText(), conta+" Alterado");
        //Opcional
        //apagaRegistro(conta);
    }

    //Entrada: M4
    //Saída: 0
    @Test
    public void excluirRegistro(){
        String conta = inserirConta();
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        driver.findElement(By.xpath("//tr[contains(., '"+conta+"')]/td/a/span[@class='glyphicon glyphicon-remove-circle']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(), "Conta removida com sucesso!");
    }

    private String inserirConta(){
        String registro = faker.harryPotter().character();
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/addConta']")).click();
        driver.findElement(By.id("nome")).sendKeys(registro);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return registro;
    }

    @After
    public void fimTeste(){
        driver.quit();
    }

    /*public void apagaRegistro(String conta){
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/contas']")).click();
        driver.findElement(By.xpath("//tr[contains(., '"+conta+"')]/td/a/span[@class='glyphicon glyphicon-remove-circle']")).click();
    }*/
}
