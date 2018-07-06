package com.activemeasure.notesapi.controller;

import com.activemeasure.notesapi.dto.NotesRequestDTO;
import com.activemeasure.notesapi.model.Note;
import com.activemeasure.notesapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

/**
 * Created by asgupta on 7/5/18.
 */



@RestController
@RequestMapping(value={"/notes"}, produces = "application/json")
public class NotesController {

    @Autowired
    NoteService noteService;

    @GetMapping
    public Set<Note> getNotes(@RequestHeader(value="Authorization") String authHeader) {
        String base64Credentials = authHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        return noteService.getNotes(credentials.split(":")[0]);
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody NotesRequestDTO notesRequestDTO, @RequestHeader(value="Authorization") String authHeader) {

        String base64Credentials = authHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        noteService.save(notesRequestDTO.getNotes(), credentials.split(":")[0]);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PatchMapping
    public ResponseEntity<String> patch(@RequestBody Note note, @RequestHeader(value="Authorization") String authHeader) {

        String base64Credentials = authHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        noteService.update(note, credentials.split(":")[0]);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody Note note, @RequestHeader(value="Authorization") String authHeader) {

        String base64Credentials = authHeader.substring("Basic".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                Charset.forName("UTF-8"));
        noteService.delete(note, credentials.split(":")[0]);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}
