package com.chat.aj.unote.Notes.Services;

import com.chat.aj.unote.Notes.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NotesRepository notesRepository;

    @Autowired
    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

}
