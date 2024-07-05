package ariano.demo.web.rest;


import ariano.demo.mapper.NoteMapper;
import ariano.demo.model.Note;
import ariano.demo.service.NoteService;
import ariano.demo.service.dto.NoteDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
@ResponseBody
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;
    private NoteMapper noteMapper;

    @GetMapping
    public List<NoteDTO> getAllNotes() {

        List<Note> allNotes = noteService.getAllNotes();

        return allNotes.stream()
                .map(noteMapper::toDto)
                .toList();

    }


    @DeleteMapping
    public String deleteAllNotes() {
        return noteService.deleteAllNotes();

    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(noteService.getById(id));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public String deleteNotes(@PathVariable Long id) {

        return noteService.deleteNote(id);

    }



    @PostMapping
    public NoteDTO saveNote(@RequestBody NoteDTO noteDTO) {

        return noteService.saveNote(noteDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotes(@RequestBody NoteDTO noteDTO, @PathVariable Long id) throws BadRequestException {


        try {
            return ResponseEntity.ok(noteService.editNote(id, noteDTO));

        } catch (EntityNotFoundException | BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

}


