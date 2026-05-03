package com.chat.aj.unote.Notes.Controllers;

import com.chat.aj.unote.Accounts.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Accounts.response.ApiResponse;
import com.chat.aj.unote.Notes.Services.VideoTimestampService;
import com.chat.aj.unote.Notes.request.CreateTimestampRequest;
import com.chat.aj.unote.Notes.request.UpdateTimestampRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/notes/{notesId}/timestamps")
@RequiredArgsConstructor
public class VideoTimestampController {
    private final VideoTimestampService timestampService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTimestamp(@PathVariable Long notesId, @RequestBody CreateTimestampRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Create timestamp success!",
                            timestampService.createVideoTimestamp(notesId, request)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse> getAll(@PathVariable Long notesId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Success",
                            timestampService.getAllVideoTimestamps(notesId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }

    @PutMapping("/{commentId}/update")
    public ResponseEntity<ApiResponse> update(@PathVariable Long notesId, @PathVariable Long commentId, @RequestBody UpdateTimestampRequest request) {
        try {
            return ResponseEntity.ok(new ApiResponse("Updated",
                    timestampService.updateVideoTimestamp(notesId, commentId, request)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long notesId, @PathVariable Long commentId) {
        try {
            timestampService.deleteVideoTimestamp(notesId, commentId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse("Incorrect note type", null));
        }
    }
}