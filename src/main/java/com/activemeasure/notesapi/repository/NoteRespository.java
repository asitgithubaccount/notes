package com.activemeasure.notesapi.repository;

import com.activemeasure.notesapi.model.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by asgupta on 7/5/18.
 */

@RepositoryRestResource(path="note")
public interface NoteRespository extends CrudRepository<Note, UUID> {

    Set<Note> findByUserId(String userId);
    Note findByNoteTextAndTitleAndUserId( String noteText, String title, String userId);

}