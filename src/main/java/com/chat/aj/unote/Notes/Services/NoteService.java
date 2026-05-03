package com.chat.aj.unote.Notes.Services;

import com.chat.aj.unote.Accounts.Entity.Accounts;
import com.chat.aj.unote.Accounts.repository.AccountsRepository;
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
    private final AccountsRepository accountsRepository;

    @Autowired
    public NoteService(NotesRepository noteRepository, UnitRepository unitRepository, AccountsRepository accountsRepository) {
        this.noteRepository = noteRepository;
        this.unitRepository = unitRepository;
        this.accountsRepository = accountsRepository;
    }

    public Long createNote(String title, String fileUrl, Long unitId, String name) {
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit", unitId));

        Accounts account = accountsRepository.findByUsername(name)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        Long userId = account.getId();

        Accounts user = accountsRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Notes note = new Notes();
        note.setTitle(title);
        note.setFileUrl(fileUrl);
        note.setType(NoteType.PDF);
        note.setUnit(unit);
        note.setUser(null);  // SET THE USER

        noteRepository.save(note);
        return note.getId();
    }
}
