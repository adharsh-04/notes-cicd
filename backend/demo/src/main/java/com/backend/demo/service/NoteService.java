package com.backend.demo.service;

import com.backend.demo.model.Note;
import com.backend.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public Note create(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note update(String id, Note noteDetails) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(noteDetails.getTitle());
            note.setContent(noteDetails.getContent());
            return noteRepository.save(note);
        }).orElse(null);
    }

    public void delete(String id) {
        noteRepository.deleteById(id);
    }
}
