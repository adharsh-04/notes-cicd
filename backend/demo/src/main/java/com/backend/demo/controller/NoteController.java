package com.backend.demo.controller;

import com.backend.demo.model.Note;
import com.backend.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note create(@RequestBody Note note) {
        return noteService.create(note);
    }

    @GetMapping
    public List<Note> getAll() {
        return noteService.getAll();
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable String id, @RequestBody Note note) {
        return noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        noteService.delete(id);
    }
}
