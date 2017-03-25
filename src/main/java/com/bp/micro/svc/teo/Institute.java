package com.bp.micro.svc.teo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Institute implements Serializable, Comparable<Institute> {
  private static final long serialVersionUID = 1L;

  public Institute(@JsonProperty("name") String name) {
    this.name = name;
  }
  private String name;
  private String id;
  private String oldId;
  private String level;
  public String getName() {
    return name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getLevel() {
    return level;
  }
  public void setLevel(String level) {
    this.level = level;
  }
  @Override
  public int compareTo(Institute o) {
    if(o == null || o.name == null || o.name.trim().isEmpty()) {
      return -1;
    }
    return this.name.trim().compareTo(o.name.trim());
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Institute other = (Institute) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
  public String getOldId() {
    return oldId;
  }
  public void setOldId(String oldId) {
    this.oldId = oldId;
  }
  @Override
  public String toString() {
    return "Institute [name=" + name + ", id=" + id + ", oldId=" + oldId + ", level=" + level + "]";
  }



}
