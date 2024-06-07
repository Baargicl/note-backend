package ariano.demo.web.rest;


import ariano.demo.mapper.NoteMapper;
import ariano.demo.model.Note;
import ariano.demo.service.NoteService;
import ariano.demo.service.dto.NoteDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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



    @PostMapping
    public NoteDTO saveNote(@RequestBody NoteDTO noteDTO) {

        return noteService.saveNote(noteDTO);

    }

}
