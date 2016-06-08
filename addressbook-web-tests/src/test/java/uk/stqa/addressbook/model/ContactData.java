package uk.stqa.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String address;
  private String group;
  private final String homeNumber;
  private final String email;

  public ContactData(String firstName, String lastName, String address, String group, String homeNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.group = group;
    this.homeNumber = homeNumber;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
