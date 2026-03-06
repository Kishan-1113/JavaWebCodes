package com.new_project.journal_entry.model;

//import java.rmi.server.ObjID;
//import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

// this is a normal POJO class
// using ORM, we need to map it to mongDb

//with this line we are connecting this class to a database of mongoDb
@Document(collection = "users")
@Getter
@Setter
public class UsersEntry {
    @Id
    private ObjectId id;

    // all usernames are unique
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    // List of all the journalEntries
    @DBRef
    private List<JournalEntry> jrnlEntrs = new ArrayList<>();

    public List<JournalEntry> getJournalEntries() {
        return jrnlEntrs;
    }

    public void addJournalEntry(JournalEntry j) {
        jrnlEntrs.add(j);
    }

}
