package com.new_project.journal_entry.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.new_project.journal_entry.model.JournalEntry;
import com.new_project.journal_entry.model.UsersEntry;
import com.new_project.journal_entry.repo.JournalEntryRepository;

@Service
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserEntreyServices userEntreyServices;

    // saves data to DB, these methods are provided by JournalEntryRepository...
    @Transactional
    public void saveData(JournalEntry journalEntry, String userName) {
        Optional<UsersEntry> op = userEntreyServices.findUser(userName);

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        UsersEntry user = op.get();
        user.getJournalEntries().add(saved);
        userEntreyServices.saveData(user);
    }

    public List<JournalEntry> getData() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        if (id != null)
            return journalEntryRepository.findById(id);
        return null;

    }

    public void delete(ObjectId id) {
        if (id != null)
            journalEntryRepository.deleteById(id);
    }

}

// controller ---> service ----> repository
