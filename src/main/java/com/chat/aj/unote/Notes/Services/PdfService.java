package com.chat.aj.unote.Notes.Services;

import com.chat.aj.unote.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Notes.Entity.Notes;
import com.chat.aj.unote.Notes.Entity.PdfComment;
import com.chat.aj.unote.Notes.NoteType;
import com.chat.aj.unote.Notes.Repository.NotesRepository;
import com.chat.aj.unote.Notes.Repository.PdfRepository;
import com.chat.aj.unote.Notes.request.CreatePdfCommentRequest;
import com.chat.aj.unote.Notes.request.UpdatePdfCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final PdfRepository pdfRepository;
    private final NotesRepository notesRepository;

    public List<PdfComment> getAllPdfComments(Long notesId) throws ResourceNotFoundException {
        Notes note = getPdfNote(notesId);
        return pdfRepository.findByNoteId(note.getId());
    }

    public PdfComment createPdfComment(Long notesId, CreatePdfCommentRequest request) throws ResourceNotFoundException {
        Notes note = getPdfNote(notesId);
        PdfComment comment = new PdfComment();
        comment.setComment(request.getComment());
        comment.setX(request.getX());
        comment.setY(request.getY());
        comment.setWidth(request.getWidth());
        comment.setHeight(request.getHeight());
        comment.setPageNumber(request.getPageNumber());
        comment.setNote(note);
        return pdfRepository.save(comment);
    }

    public PdfComment updatePdfComment(Long notesId, Long commentId, UpdatePdfCommentRequest request) throws ResourceNotFoundException {
        getPdfNote(notesId);
        PdfComment existing = pdfRepository.findByCommentIdAndNoteId(commentId, notesId)
                .orElseThrow(() -> new ResourceNotFoundException("PDF comment not found"));

        existing.setComment(request.getComment());
        existing.setX(request.getX());
        existing.setY(request.getY());
        existing.setWidth(request.getWidth());
        existing.setHeight(request.getHeight());
        existing.setPageNumber(request.getPageNumber());
        return pdfRepository.save(existing);
    }

    public void deletePdfComment(Long notesId, Long commentId) throws ResourceNotFoundException {
        getPdfNote(notesId);
        PdfComment existing = pdfRepository.findByCommentIdAndNoteId(commentId, notesId)
                .orElseThrow(() -> new ResourceNotFoundException("PDF comment not found"));
        pdfRepository.delete(existing);
    }

    private Notes getPdfNote(Long notesId) throws ResourceNotFoundException {
        Notes note = notesRepository.findById(notesId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        if (note.getType() != NoteType.PDF) {
            throw new ResourceNotFoundException("Comments only allowed for PDF notes");
        }
        return note;
    }
}
