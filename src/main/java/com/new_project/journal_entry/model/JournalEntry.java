package com.new_project.journal_entry.model;

//import java.rmi.server.ObjID;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// this is a normal POJO class
// using ORM, we need to map it to mongDb

//with this line we are connecting this class to a database of mongoDb
@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
