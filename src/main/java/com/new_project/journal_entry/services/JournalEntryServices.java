package com.new_project.journal_entry.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.mongodb.lang.NonNull;
import com.new_project.journal_entry.model.JournalEntry;
import com.new_project.journal_entry.repo.JournalEntryRepository;

@Service
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // saves data to DB, these methods are provided by JournalEntryRepository...
    public void saveData(@NonNull JournalEntry journalEntry) {
        if (journalEntry != null)
            journalEntryRepository.save(journalEntry);
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
