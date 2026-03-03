package com.new_project.journal_entry.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.new_project.journal_entry.model.UsersEntry;

public interface UserEntryRepository extends MongoRepository<UsersEntry, ObjectId> {

    Optional<UsersEntry> findByUserName(String userName);

}
