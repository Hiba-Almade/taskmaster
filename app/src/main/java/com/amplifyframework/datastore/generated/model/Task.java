package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks")
@Index(name = "byTeam", fields = {"teamId"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField TEAM_ID = field("Task", "teamId");
  public static final QueryField TITLE = field("Task", "title");
  public static final QueryField BODY = field("Task", "body");
  public static final QueryField STATE = field("Task", "state");
  public static final QueryField FILE_KEY = field("Task", "fileKey");
  public static final QueryField ALTITUDE = field("Task", "altitude");
  public static final QueryField LONGITUDE = field("Task", "longitude");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String teamId;
  private final @ModelField(targetType="String") String title;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="String") String state;
  private final @ModelField(targetType="String") String fileKey;
  private final @ModelField(targetType="Float") Double altitude;
  private final @ModelField(targetType="Float") Double longitude;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTeamId() {
      return teamId;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public String getFileKey() {
      return fileKey;
  }
  
  public Double getAltitude() {
      return altitude;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String teamId, String title, String body, String state, String fileKey, Double altitude, Double longitude) {
    this.id = id;
    this.teamId = teamId;
    this.title = title;
    this.body = body;
    this.state = state;
    this.fileKey = fileKey;
    this.altitude = altitude;
    this.longitude = longitude;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getTeamId(), task.getTeamId()) &&
              ObjectsCompat.equals(getTitle(), task.getTitle()) &&
              ObjectsCompat.equals(getBody(), task.getBody()) &&
              ObjectsCompat.equals(getState(), task.getState()) &&
              ObjectsCompat.equals(getFileKey(), task.getFileKey()) &&
              ObjectsCompat.equals(getAltitude(), task.getAltitude()) &&
              ObjectsCompat.equals(getLongitude(), task.getLongitude()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTeamId())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getFileKey())
      .append(getAltitude())
      .append(getLongitude())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("teamId=" + String.valueOf(getTeamId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("fileKey=" + String.valueOf(getFileKey()) + ", ")
      .append("altitude=" + String.valueOf(getAltitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Task justId(String id) {
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      teamId,
      title,
      body,
      state,
      fileKey,
      altitude,
      longitude);
  }
  public interface TeamIdStep {
    BuildStep teamId(String teamId);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id);
    BuildStep title(String title);
    BuildStep body(String body);
    BuildStep state(String state);
    BuildStep fileKey(String fileKey);
    BuildStep altitude(Double altitude);
    BuildStep longitude(Double longitude);
  }
  

  public static class Builder implements TeamIdStep, BuildStep {
    private String id;
    private String teamId;
    private String title;
    private String body;
    private String state;
    private String fileKey;
    private Double altitude;
    private Double longitude;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          teamId,
          title,
          body,
          state,
          fileKey,
          altitude,
          longitude);
    }
    
    @Override
     public BuildStep teamId(String teamId) {
        Objects.requireNonNull(teamId);
        this.teamId = teamId;
        return this;
    }
    
    @Override
     public BuildStep title(String title) {
        this.title = title;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep state(String state) {
        this.state = state;
        return this;
    }
    
    @Override
     public BuildStep fileKey(String fileKey) {
        this.fileKey = fileKey;
        return this;
    }
    
    @Override
     public BuildStep altitude(Double altitude) {
        this.altitude = altitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String teamId, String title, String body, String state, String fileKey, Double altitude, Double longitude) {
      super.id(id);
      super.teamId(teamId)
        .title(title)
        .body(body)
        .state(state)
        .fileKey(fileKey)
        .altitude(altitude)
        .longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder fileKey(String fileKey) {
      return (CopyOfBuilder) super.fileKey(fileKey);
    }
    
    @Override
     public CopyOfBuilder altitude(Double altitude) {
      return (CopyOfBuilder) super.altitude(altitude);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
  }
  
}
