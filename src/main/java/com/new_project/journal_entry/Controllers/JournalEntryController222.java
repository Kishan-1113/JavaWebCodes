package com.new_project.journal_entry.Controllers;

import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.new_project.journal_entry.model.JournalEntry;
import com.new_project.journal_entry.model.UsersEntry;
import com.new_project.journal_entry.services.JournalEntryServices;
import com.new_project.journal_entry.services.UserEntreyServices;

// import org.springframework.web.bind.annotation.PostMapping;
// //import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/journal")
public class JournalEntryController222 {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserEntreyServices userEntreyServices;

    @GetMapping("/entries/{userName}")
    public ResponseEntity<List<JournalEntry>> getEntry(@PathVariable String userName) {
        Optional<UsersEntry> op = userEntreyServices.findUser(userName);
        if (op.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.FOUND).body(op.get().getJournalEntries());
    }

    @PostMapping("/add/{userName}")
    public ResponseEntity<JournalEntry> add(
            @RequestBody JournalEntry newEntry,
            @PathVariable String userName) {
        // adds Current date and time
        try {
            journalEntryServices.saveData(newEntry, userName);

            return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> update(
            @PathVariable ObjectId id,
            @PathVariable String userName,
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

        journalEntryServices.saveData(old, userName);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(old);
    }
}
