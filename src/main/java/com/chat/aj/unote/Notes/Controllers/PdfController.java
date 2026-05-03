package com.chat.aj.unote.Notes.Controllers;

import com.chat.aj.unote.Accounts.response.ApiResponse;
import com.chat.aj.unote.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Notes.Services.PdfService;
import com.chat.aj.unote.Notes.request.CreatePdfCommentRequest;
import com.chat.aj.unote.Notes.request.UpdatePdfCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/notes/{notesId}/pdfs-comments")
@RequiredArgsConstructor
public class PdfController {
    private final PdfService pdfService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@PathVariable Long notesId, @RequestBody CreatePdfCommentRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Created", pdfService.createPdfComment(notesId, request)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse> getAll(@PathVariable Long notesId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Success", pdfService.getAllPdfComments(notesId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }

    @PutMapping("/{commentId}/update")
    public ResponseEntity<ApiResponse> update(@PathVariable Long notesId, @PathVariable Long commentId,
                                              @RequestBody UpdatePdfCommentRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Updated", pdfService.updatePdfComment(notesId, commentId, request)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long notesId, @PathVariable Long commentId) {
        try {
            pdfService.deletePdfComment(notesId, commentId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }
}