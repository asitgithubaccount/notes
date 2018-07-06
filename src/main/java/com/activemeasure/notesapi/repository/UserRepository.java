package com.activemeasure.notesapi.repository;

import com.activemeasure.notesapi.model.Note;
import com.activemeasure.notesapi.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;
import java.util.UUID;

/**
 * Created by asgupta on 7/5/18.
 */
@RepositoryRestResource(path="user")
public interface UserRepository extends CrudRepository<User, String> {

    //User findByUserId(UUID userId);
}