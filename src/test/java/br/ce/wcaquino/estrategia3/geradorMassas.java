package br.ce.wcaquino.estrategia3;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLException;

public class geradorMassas {

    public static final String CHAVE_CONTA_SB = "CONTA_SB";
    public void gerarContaSeuBarriga() throws SQLException, ClassNotFoundException {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
        driver.findElement(By.id("email")).sendKeys("denisguimaraes2012@gmail.com");
        driver.findElement(By.id("senha")).sendKeys("@Dd050698");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Faker faker = new Faker();
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[@href='/addConta']")).click();
        String contaCriada = faker.gameOfThrones().character() + " " + faker.gameOfThrones().dragon();
        driver.findElement(By.id("nome")).sendKeys(contaCriada);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.quit();

        new MassaDAO().inserirMassa(CHAVE_CONTA_SB, contaCriada);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //geradorMassas gerador = new geradorMassas();
        //for (int i = 0; i < 5; i++){
        //    gerador.gerarContaSeuBarriga();
        //}
        String massa = new MassaDAO().obterMassa(CHAVE_CONTA_SB);
        System.out.println(massa);

    }
}
