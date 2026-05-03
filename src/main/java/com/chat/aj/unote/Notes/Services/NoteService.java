package com.chat.aj.unote.Notes.Services;

import com.chat.aj.unote.Accounts.Entity.Accounts;
import com.chat.aj.unote.Accounts.Service.AccountsService;
import com.chat.aj.unote.Accounts.repository.AccountsRepository;
import com.chat.aj.unote.Config.R2Service;
import com.chat.aj.unote.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Exceptions.UnauthorizedException;
import com.chat.aj.unote.Notes.Entity.Notes;
import com.chat.aj.unote.Notes.Entity.Unit;
import com.chat.aj.unote.Notes.NoteType;
import com.chat.aj.unote.Notes.Repository.NotesRepository;
import com.chat.aj.unote.Notes.Repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
public class NoteService {

    private final NotesRepository noteRepository;
    private final UnitRepository unitRepository;
    private final AccountsRepository accountsRepository;
    private final AccountsService accountsService;
    private final R2Service r2Service;

    @Autowired
    public NoteService(NotesRepository noteRepository, UnitRepository unitRepository, AccountsRepository accountsRepository, AccountsService accountsService, R2Service r2Service) {
        this.noteRepository = noteRepository;
        this.unitRepository = unitRepository;
        this.accountsRepository = accountsRepository;
        this.accountsService = accountsService;
        this.r2Service = r2Service;
    }

    public Long createNote(String title, String fileUrl, Long unitId, String name, MultipartFile file) {
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException("Unit", unitId));

        Accounts account = accountsRepository.findByUsername(name)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        Notes note = new Notes();
        note.setTitle(title);
        note.setFileUrl(fileUrl);
        note.setType(NoteType.PDF);
        note.setUnit(unit);
        note.setUser(account);
        note.setFileName(file.getOriginalFilename());
        note.setFileType(file.getContentType());
        note.setFileSize(file.getSize());
        note.setType(inferType(file.getContentType()));
        noteRepository.save(note);
        return note.getId();
    }

    private NoteType inferType(String contentType) {
        if (contentType == null) return NoteType.TXTFILE;

        return switch (contentType) {
            case "application/pdf" -> NoteType.PDF;
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation",
                 "application/vnd.ms-powerpoint" -> NoteType.PTX;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                 "application/msword" -> NoteType.DOCX;
            case "text/plain" -> NoteType.TXTFILE;
            case "image/jpeg", "image/png", "image/gif", "image/webp" -> NoteType.IMAGE;
            case "video/mp4", "video/mpeg", "video/quicktime", "video/webm" -> NoteType.VIDEO;
            default -> NoteType.TXTFILE;
        };
    }

    public void deleteNote(Long noteId, String name) {
        Optional<Notes> note = noteRepository.findById(noteId);
        if(note.isEmpty()) throw new ResourceNotFoundException("Note not found");
        Optional<Accounts> a = accountsRepository.findByUsername(name);
        if(a.isEmpty()) throw new ResourceNotFoundException("Account not found");
        if(!note.get().getUser().equals(a.get())) throw new UnauthorizedException("You do not own this note!");

        r2Service.deleteFile(note.get().getFileUrl());

        noteRepository.delete(note.get());
    }
}
