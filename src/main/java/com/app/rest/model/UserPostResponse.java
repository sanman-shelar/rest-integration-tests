package com.app.rest.model;

public class UserPostResponse {

  private int id;
  private String name;
  private String job;
  private String createdAt;

  /**
   * 
   */
  public UserPostResponse() {}

  /**
   * @param id
   * @param name
   * @param job
   * @param createdAt
   */
  public UserPostResponse(int id, String name, String job, String createdAt) {
    super();
    this.id = id;
    this.name = name;
    this.job = job;
    this.createdAt = createdAt;
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
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the job
   */
  public String getJob() {
    return job;
  }

  /**
   * @param job the job to set
   */
  public void setJob(String job) {
    this.job = job;
  }

  /**
   * @return the createdAt
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
