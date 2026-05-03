package com.chat.aj.unote.Notes.Services;

import com.chat.aj.unote.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Notes.Entity.Notes;
import com.chat.aj.unote.Notes.Entity.Unit;
import com.chat.aj.unote.Notes.NoteType;
import com.chat.aj.unote.Notes.Repository.NotesRepository;
import com.chat.aj.unote.Notes.Repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NoteService {

    private final NotesRepository noteRepository;
    private final UnitRepository unitRepository;

    @Autowired
    public NoteService(NotesRepository noteRepository, UnitRepository unitRepository) {
        this.noteRepository = noteRepository;
        this.unitRepository = unitRepository;
    }

    public Long createNote(String title, String fileUrl, Long unitId) {
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit", unitId));

        Notes note = new Notes();
        note.setTitle(title);
        note.setFileUrl(fileUrl);
        note.setType(NoteType.DOCUMENT);
        note.setUnit(unit);

        noteRepository.save(note);
        return note.getId();
    }
}
