package uk.stqa.mantis.model;

/**
 * Created by natla on 25/07/2016.
 */
public class Project {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }
}
