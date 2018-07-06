package com.activemeasure.notesapi.service;

import com.activemeasure.notesapi.model.Note;
import com.activemeasure.notesapi.model.User;
import com.activemeasure.notesapi.repository.NoteRespository;
import com.activemeasure.notesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by asgupta on 7/5/18.
 */
@Service
public class NoteService {

    @Autowired
    NoteRespository noteRespository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Set<Note> getNotes(String userId) {

        return noteRespository.findByUserId(userId);
    }

    @Transactional
    public Iterable<Note> save(Iterable<Note> notes, String userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            for( Note note : notes) {
                note.setUser(user.get());
            }
            return noteRespository.saveAll(notes);
        }

        throw new IllegalArgumentException("user not found ");
    }

    @Transactional
    public Note save(Note note, String userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            note.setUser(user.get());
            return noteRespository.save(note);
        }

        throw new IllegalArgumentException("user not found ");
    }

    @Transactional
    public Note update(Note note, String userId) {

        Optional<Note> noteFromDB = noteRespository.findById(note.getId());
        Optional<User> user = userRepository.findById(userId);
        if(noteFromDB.isPresent()) {
            Note noteToBeUpdated = noteFromDB.get();
            noteToBeUpdated.setTitle(note.getTitle());
            noteToBeUpdated.setNoteText(note.getNoteText());
            return noteRespository.save(noteToBeUpdated);
        }

        throw new IllegalArgumentException("user not found ");
    }

    public void delete(Note note, String userId) {
        Note noteToDelete = noteRespository.findByNoteTextAndTitleAndUserId(note.getNoteText(), note.getTitle(), userId);
        if( noteToDelete == null) {
            return;
        }
        noteRespository.delete(noteToDelete);
    }
}
