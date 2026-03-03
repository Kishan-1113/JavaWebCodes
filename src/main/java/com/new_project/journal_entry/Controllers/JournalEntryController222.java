package com.new_project.journal_entry.Controllers;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.new_project.journal_entry.model.JournalEntry;
import com.new_project.journal_entry.services.JournalEntryServices;

// import org.springframework.web.bind.annotation.PostMapping;
// //import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/journal")
public class JournalEntryController222 {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @GetMapping("/entries")
    public ResponseEntity<List<JournalEntry>> getEntry() {
        return ResponseEntity.status(HttpStatus.FOUND).body(journalEntryServices.getData());
    }

    @PostMapping("/add")
    public ResponseEntity<JournalEntry> add(@RequestBody @Validated JournalEntry newEntry) {
        // adds Current date and time
        newEntry.setDate(LocalDateTime.now());

        journalEntryServices.saveData(newEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(journalEntryServices.findById(id).orElse(null));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ObjectId id) {
        journalEntryServices.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> update(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry entry) {

        Optional<JournalEntry> optional = journalEntryServices.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        JournalEntry old = optional.get();

        if (entry.getTitle() != null && !entry.getTitle().trim().isEmpty()) {
            old.setTitle(entry.getTitle());
        }

        if (entry.getContent() != null && !entry.getContent().trim().isEmpty()) {
            old.setContent(entry.getContent());
        }

        journalEntryServices.saveData(old);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(old);
    }
}
