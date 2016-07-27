package uk.stqa.mantis.model;

/**
 * Created by natla on 16/07/2016.
 */
public class MailMessage {
  public String to;
  public String text;

  public MailMessage(String to, String text){
    this.to = to;
    this.text = text;
  }
}
