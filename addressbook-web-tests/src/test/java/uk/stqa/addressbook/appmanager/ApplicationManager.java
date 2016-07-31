package uk.stqa.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by natla on 02/06/2016.
 */
public class ApplicationManager {
    private final String browser;
    private final Properties properties;
    WebDriver wd;

    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();

        if ("".equals(properties.getProperty("selenium.server"))) {
          if (Objects.equals(browser, BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
          } else if (Objects.equals(browser, BrowserType.CHROME)) {
            wd = new ChromeDriver();
          }
        } else {
          DesiredCapabilities capabilities = new DesiredCapabilities();
          capabilities.setBrowserName(browser);
          wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseURL"));
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("login"), properties.getProperty("password"));
    }

    public void stop() {
    wd.quit();
  }

    public NavigationHelper goTo() {
    return navigationHelper;
  }

    public GroupHelper group() {
    return groupHelper;
  }

    public ContactHelper contact() {
    return contactHelper;
  }

    public DbHelper db() {return dbHelper;}
}
