package com.chat.aj.unote.Notes.Repository;

import com.chat.aj.unote.Notes.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

}
