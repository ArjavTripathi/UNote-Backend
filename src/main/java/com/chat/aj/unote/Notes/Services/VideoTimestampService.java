package com.chat.aj.unote.Notes.Services;

import com.chat.aj.unote.Accounts.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Notes.Entity.Notes;
import com.chat.aj.unote.Notes.NoteType;
import com.chat.aj.unote.Notes.Repository.NotesRepository;
import com.chat.aj.unote.Notes.request.CreateTimestampRequest;
import com.chat.aj.unote.Notes.Entity.VideoTimestamp;
import com.chat.aj.unote.Notes.Repository.VideoTimestampRepository;
import com.chat.aj.unote.Notes.request.UpdateTimestampRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoTimestampService {
    private final VideoTimestampRepository videoTimestampRepository;
    private final NotesRepository notesRepository;

    public List<VideoTimestamp> getAllVideoTimestamps(Long notesId) throws ResourceNotFoundException {
        Notes note = getVideoNote(notesId);
        return videoTimestampRepository.findByNoteId(note.getId());
    }

    public VideoTimestamp createVideoTimestamp(Long notesId, CreateTimestampRequest request) throws ResourceNotFoundException {
        Notes note = getVideoNote(notesId);
        VideoTimestamp vt = new VideoTimestamp();
        vt.setTimestamp(request.getTimestamp());
        vt.setComment(request.getComment());
        vt.setNote(note);
        return videoTimestampRepository.save(vt);
    }

    public VideoTimestamp updateVideoTimestamp(Long notesId, Long commentId, UpdateTimestampRequest request) throws ResourceNotFoundException {
        getVideoNote(notesId);
        VideoTimestamp vt = videoTimestampRepository.findByCommentIdAndNoteId(commentId, notesId)
                .orElseThrow(() -> new ResourceNotFoundException("Timestamp not found"));

        vt.setComment(request.getComment());
        vt.setTimestamp(request.getTimestamp());
        return videoTimestampRepository.save(vt);
    }

    public void deleteVideoTimestamp(Long notesId, Long commentId) throws ResourceNotFoundException {
        getVideoNote(notesId);
        VideoTimestamp vt = videoTimestampRepository.findByCommentIdAndNoteId(commentId, notesId)
                .orElseThrow(() -> new ResourceNotFoundException("Timestamp not found"));
        videoTimestampRepository.delete(vt);
    }

    private Notes getVideoNote(Long notesId) throws ResourceNotFoundException {
        Notes note = notesRepository.findById(notesId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        if (note.getType() != NoteType.VIDEO) {
            throw new ResourceNotFoundException("Timestamps only allowed for VIDEO notes");
        }
        return note;
    }
}