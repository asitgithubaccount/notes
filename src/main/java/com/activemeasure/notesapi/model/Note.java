package com.activemeasure.notesapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;



@Entity
@Table(name = "note")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class Note implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false, name = "title", length = 50)
    @Size(max=50)
    private String title;

    @Column(name = "note_text", nullable = false, length=1000)
    @Size(max = 1000)
    private String noteText;

    @Column(name = "create_time")
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createTime;
    @Column(name = "last_updated_time")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp lastUpdateTime;

    @JsonIgnore
    @OneToOne
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note1 = (Note) o;

        if (!id.equals(note1.id)) return false;
        if (!title.equals(note1.title)) return false;
        if (noteText != null ? !noteText.equals(note1.noteText) : note1.noteText != null) return false;
        return user.equals(note1.user);

    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + noteText.hashCode();
        return result;
    }
}
