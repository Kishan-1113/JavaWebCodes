package com.new_project.journal_entry.Controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journal_entry = new HashMap<>();

    @GetMapping("/entries")
    public List<JournalEntry> getEntry() {
        return new ArrayList<>(journal_entry.values());
    }

    @PostMapping("/add")
    public ResponseEntity<JournalEntry> add(@RequestBody JournalEntry newEntry) {
        if (journal_entry.containsKey(newEntry.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        journal_entry.put(newEntry.getId(), newEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        if (!journal_entry.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        journal_entry.remove(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable Long id) {
        if (!journal_entry.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(journal_entry.get(id));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> update(
            @PathVariable long id,
            @RequestBody JournalEntry entry) {

        if (!journal_entry.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        journal_entry.put(id, entry);
        return ResponseEntity.ok(entry);
    }
}
