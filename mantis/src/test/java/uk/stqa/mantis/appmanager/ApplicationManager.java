package uk.stqa.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by natla on 02/06/2016.
 */
public class ApplicationManager {
    private final String browser;
    private final Properties properties;
    private RegistrationHelper registrationHelper;
    private WebDriver wd;
    private FtpHelper ftp;
    private MailHelper mail;

  public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
           registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp(){
      if (ftp == null) {
        ftp = new FtpHelper(this);
      }
      return ftp;
    }

  public MailHelper mail(){
    if (mail == null) {
      mail = new MailHelper(this);
    }
    return mail;
  }

    public WebDriver getDriver() {
        if ( wd == null) {
            if (Objects.equals(browser, BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (Objects.equals(browser, BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }

            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseURL"));
        }
        return wd;
    }
}
