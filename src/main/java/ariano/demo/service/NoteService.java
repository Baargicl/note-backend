package ariano.demo.service;

import ariano.demo.mapper.NoteMapper;
import ariano.demo.model.Note;
import ariano.demo.repository.NoteRepository;
import ariano.demo.service.dto.NoteDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class NoteService {

    private NoteMapper noteMapper;
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


    public NoteDTO saveNote(NoteDTO noteDTO) {

        Note model = noteMapper.toModel(noteDTO);
        Note saved = noteRepository.save(model);

        return noteMapper.toDto(saved);
    }


}
