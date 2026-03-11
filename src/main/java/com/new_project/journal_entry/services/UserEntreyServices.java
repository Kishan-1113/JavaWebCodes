package com.new_project.journal_entry.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import com.mongodb.lang.NonNull;
import com.new_project.journal_entry.model.UsersEntry;
import com.new_project.journal_entry.repo.UserEntryRepository;

@Component
public class UserEntreyServices {

    @Autowired
    private UserEntryRepository userEntryRepository;

    // saves data to DB, these methods are provided by JournalEntryRepository...
    public void saveData(UsersEntry user) {
        if (user != null)
            userEntryRepository.save(user);
    }

    public List<UsersEntry> getData() {
        return userEntryRepository.findAll();
    }

    public Optional<UsersEntry> findById(ObjectId id) {
        if (id == null) {
            return Optional.empty();
        }
        return userEntryRepository.findById(id);
    }

    public Optional<UsersEntry> findUser(String userName) {
        if (userName == null) {
            return Optional.empty();
        }
        return userEntryRepository.findByUserName(userName);
    }

    public void delete(ObjectId id) {
        if (id != null)
            userEntryRepository.deleteById(id);
    }

}
