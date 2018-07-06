package com.activemeasure.notesapi.dto;

import com.activemeasure.notesapi.model.Note;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by asgupta on 7/5/18.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NotesRequestDTO {
    Set<Note> notes = new HashSet<>();

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

}
