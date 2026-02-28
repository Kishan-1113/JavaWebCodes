package com.new_project.journal_entry.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
