package uk.stqa.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by natla on 16/07/2016.
 */
public class Users extends ForwardingSet<UserData> {

  private Set<UserData> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<UserData>(users.delegate());
  }

  public Users() {
    this.delegate = new HashSet<>();
  }

  public Users(Collection<UserData> users) {
    this.delegate = new HashSet<UserData>(users);
  }

  @Override
  protected Set<UserData> delegate() {
    return delegate;
  }

  public Users withAdded(UserData user) {
    Users users = new Users(this);
    users.add(user);
    return users;
  }

  public Users without(UserData user) {
    Users users = new Users(this);
    users.remove(user);
    return users;
  }
}