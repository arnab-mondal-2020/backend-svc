package com.bp.micro.svc.teo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Major implements Serializable, Comparable<Major> {
  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String degreeId;

  public Major(@JsonProperty("id") String id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDegreeId() {
    return degreeId;
  }

  public void setDegreeId(String degreeId) {
    this.degreeId = degreeId;
  }

  @Override
  public int compareTo(Major o) {
    if (this.id.compareTo(o.id) == 0 || this.name.compareTo(o.name) == 0) {
      return 0;
    }
    return -1;
  }

  @Override
  public String toString() {
    return "Major [id=" + id + ", name=" + name + ", degreeId=" + degreeId + "]";
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
    Major other = (Major) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
}
