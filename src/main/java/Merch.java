import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkool on 9/4/2018.
 */
public class Merch {
    private String merchProductsUrl = "https://merch.amazon.com/manage/products";
    private String productXpath = "//a[contains(@class, 'a-link-normal aok-align-center')]";
    private String titleXpath = "//*[@id='data-draft-name-en-us']";
    private String bullet1Xpath = "//*[@id='data-draft-bullet-points-bullet1-en-us']";
    private String bullet2Xpath = "//*[@id='data-draft-bullet-points-bullet2-en-us']";
    private String imageXpath = "//*[@id='gear-tshirt-image']/img";
    private String paginationXpath = "//*[@id='pagination-controls-page-jump-controls-input']";
    private String pageNumbersXpath = "//*[@id='pagination-controls-page-button-list']//li/a";
    private String editXpath = "//*[@class='a-dropdown-item']";
    ArrayList<String> links = new ArrayList<String>();
    ArrayList<Shirt> shirts = new ArrayList<Shirt>();

    ArrayList<Shirt> getAllShirts(WebDriver driver) throws InterruptedException, IOException {
        String email = "";
        String password = "";
        driver.navigate().to(this.merchProductsUrl);
        driver.findElement(By.xpath("//*[@id='ap_email']")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='ap_password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='signInSubmit']")).click();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> pageNumbers = driver.findElements(By.xpath(pageNumbersXpath));
        int lastPage = Integer.parseInt(pageNumbers.get(pageNumbers.size() - 2).getText());

        for (int i = 0; i < lastPage; i += 1) {
            Thread.sleep(5000);

            for (int j = 0; j < 15; j+= 1) {
                Thread.sleep(3000);
                List<WebElement> actionButtons;
               actionButtons = driver.findElements(By.xpath("//*[@class='a-dropdown-prompt']"));

               actionButtons.get(j + 1).click();
               driver.findElements(By.xpath(editXpath)).get(0).click();

               Thread.sleep(3000);


                String title = driver.findElement(By.xpath(titleXpath)).getAttribute("value").trim();
                String bullet1 = driver.findElement(By.xpath(bullet1Xpath)).getAttribute("value").trim();
                String bullet2 = driver.findElement(By.xpath(bullet2Xpath)).getAttribute("value").trim();

                WebElement image = driver.findElement(By.xpath(imageXpath));
                String imageSrc = image.getAttribute("src");
                URL imageURL = new URL(imageSrc);
                BufferedImage shirtImage = ImageIO.read(imageURL);

                Shirt s = new Shirt(title, bullet1, bullet2, shirtImage);

                System.out.println(s);
                this.shirts.add(s);

                ImageIO.write(shirtImage, "png", new File("test-shirt-download.png"));

                driver.navigate().back();
            }


            driver.findElement(By.xpath(this.paginationXpath)).sendKeys("" + (i + 2) + Keys.ENTER);
        }

    return this.shirts;
    }

}
