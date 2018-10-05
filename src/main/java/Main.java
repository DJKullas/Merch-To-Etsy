import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dkool on 9/4/2018.
 */
public class Main {

    public static void main(String args[]) throws InterruptedException, IOException {
       /* System.setProperty("webdriver.gecko.driver", "C:\\Users\\dkool\\Documents\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        Merch m = new Merch();
        m.getAllShirts(driver);*/

       Printful p = new Printful();
       p.inputTags();
    }
}
