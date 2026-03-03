package com.new_project.journal_entry.Controllers;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.new_project.journal_entry.model.UsersEntry;
//import com.new_project.journal_entry.model.JournalEntry;
import com.new_project.journal_entry.services.UserEntreyServices;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserEntreyServices userEntryServices;

    // All users
    @GetMapping
    public ResponseEntity<List<UsersEntry>> getEntry() {
        return ResponseEntity.status(HttpStatus.FOUND).body(userEntryServices.getData());
    }

    @PostMapping("/new")
    public ResponseEntity<UsersEntry> add(@RequestBody UsersEntry newEntry) {

        Optional<UsersEntry> optional = userEntryServices.findUser(newEntry.getUserName());
        if (!optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        userEntryServices.saveData(newEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsersEntry> getById(@PathVariable ObjectId id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userEntryServices.findById(id).orElse(null));
    }

    @PutMapping("/update")
    public ResponseEntity<UsersEntry> updateUser(@RequestBody UsersEntry user) {
        Optional<UsersEntry> optional = userEntryServices.findUser(user.getUserName());
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        UsersEntry oldUser = optional.get();

        if (user.getUserName() != null && !user.getUserName().trim().isEmpty()) {
            oldUser.setUserName(user.getUserName());
        }

        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
            oldUser.setPassword(user.getPassword());
        }

        userEntryServices.saveData(oldUser);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(oldUser);
    }

}
