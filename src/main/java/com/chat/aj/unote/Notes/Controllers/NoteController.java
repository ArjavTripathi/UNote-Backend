package com.chat.aj.unote.Notes.Controllers;

import com.chat.aj.unote.Config.R2Service;
import com.chat.aj.unote.Notes.Services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/app/v1/notes")
public class NoteController {
    private final NoteService noteService;
    private final R2Service r2Service;

    @Autowired
    public NoteController(NoteService noteService, R2Service r2Service) {
        this.noteService = noteService;
        this.r2Service = r2Service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNote(
            @RequestParam("file") MultipartFile file,
            @RequestParam("unitId") Long unitId,
            @RequestParam("title") String title,
            Principal principal) throws IOException {  // ADD THIS

        String fileUrl = r2Service.uploadFile(file);
        Long id = noteService.createNote(title, fileUrl, unitId, principal.getName());
        return ResponseEntity.ok(Map.of("id", id, "fileUrl", fileUrl));
    }
}
