package uk.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {
  @Id
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Type(type = "text")
  private String address;

  @Transient
  private String group;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homeT;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobileT;

  @Column(name = "work")
  @Type(type = "text")
  private String workT;

  @Transient
  private String allPhones;

  @Expose
  @Type(type = "text")
  private String email;

  @Type(type = "text")
  private String email2;

  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;
  @Transient
  private String allInfo;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public int getId() {
    return id;
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

  public String getAllPhones() {
    return allPhones;
  }

  public String getHomeT() {
    return homeT;
  }

  public String getMobileT() {
    return mobileT;
  }

  public String getWorkT() {
    return workT;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getGroup() {
    return group;
  }

  public String getInfo() {
    return allInfo;
  }

  public String getPhoto() {
    return photo;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withHomeT(String homeT) {
    this.homeT = homeT;
    return this;
  }


  public ContactData withMobileT(String mobileT) {
    this.mobileT = mobileT;
    return this;
  }

  public ContactData withWorkT(String workT) {
    this.workT = workT;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllInfo(String allInfo) {
    this.allInfo = allInfo;
    return this;
  }

  public ContactData withPhoto(String photo) {
    this.photo = photo;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
