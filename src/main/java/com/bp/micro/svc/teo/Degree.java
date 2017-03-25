package com.bp.micro.svc.teo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Degree implements Serializable, Comparable<Degree> {
  private static final long serialVersionUID = 1L;

  public Degree(@JsonProperty("id") String id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }
  public Degree(String id) {
    this(id, null);
  }

  private String id;
  private String name;

  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  @Override
  public int compareTo(Degree o) {
    if(this.id.compareTo(o.id) == 0 || this.name.compareTo(o.name) ==0) {
      return 0;
    }
    return -1;
  }
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Degree [id=");
    builder.append(id);
    builder.append(", name=");
    builder.append(name);
    builder.append("]");
    return builder.toString();
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
    Degree other = (Degree) obj;
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
