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
  private FtpHelper ftpHelper;
  private MailHelper mailHelper;
  private AdminHelper adminHelper;
  private DbHelper dbHelper;
  private UserHelper userHelper;
  private SoapHelper soapHelper;

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
    if (ftpHelper == null) {
      ftpHelper = new FtpHelper(this);
    }
    return ftpHelper;
  }

  public MailHelper mail(){
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public AdminHelper admin(){
    if (adminHelper == null) {
      adminHelper = new AdminHelper(this);
    }
    return adminHelper;
  }

  public UserHelper user(){
    if (userHelper == null) {
      userHelper = new UserHelper(this);
    }
    return userHelper;
  }

  public DbHelper db() {
    if (dbHelper == null) {
      dbHelper = new DbHelper(this);
    }
    return dbHelper;
  }

  public SoapHelper soap() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
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
