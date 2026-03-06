package com.new_project.journal_entry.Controllers;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//import org.bson.types.ObjectId;
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

        try {
            Optional<UsersEntry> optional = userEntryServices.findUser(newEntry.getUserName());
            if (!optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            userEntryServices.saveData(newEntry);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsersEntry> getById(@PathVariable String username) {
        try {
            Optional<UsersEntry> optional = userEntryServices.findUser(username);
            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.status(HttpStatus.FOUND).body(userEntryServices.findUser(username).orElse(null));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<UsersEntry> deleteUser(@PathVariable String userName) {
        Optional<UsersEntry> optional = userEntryServices.findUser(userName);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        userEntryServices.delete(optional.get().getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<UsersEntry> updateUser(
            @PathVariable String userName,
            @RequestBody UsersEntry user) {

        try {
            Optional<UsersEntry> optional = userEntryServices.findUser(userName);
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

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
