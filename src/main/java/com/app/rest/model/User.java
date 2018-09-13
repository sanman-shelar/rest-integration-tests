package com.app.rest.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("data")
public class User {

  private int id;
  private String first_name;
  private String last_name;
  private String avatar;

  public User() {
  }

  /**
   * @param id
   * @param first_name
   * @param last_name
   * @param avatar
   */
  public User(int id, String first_name, String last_name, String avatar) {
    super();
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.avatar = avatar;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the first_name
   */
  public String getFirst_name() {
    return first_name;
  }

  /**
   * @param first_name the first_name to set
   */
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  /**
   * @return the last_name
   */
  public String getLast_name() {
    return last_name;
  }

  /**
   * @param last_name the last_name to set
   */
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  /**
   * @return the avatar
   */
  public String getAvatar() {
    return avatar;
  }

  /**
   * @param avatar the avatar to set
   */
  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }  
}
