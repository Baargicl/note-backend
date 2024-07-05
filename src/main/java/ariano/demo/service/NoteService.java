package ariano.demo.service;

import ariano.demo.mapper.NoteMapper;
import ariano.demo.model.Note;
import ariano.demo.repository.NoteRepository;
import ariano.demo.service.dto.NoteDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class NoteService {

    private NoteMapper noteMapper;
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public NoteDTO getById(Long id) {
        Optional<Note> optNote =  noteRepository.findById(id);
        if(optNote.isPresent()) {
            return noteMapper.toDto(optNote.get());
        }

        throw new EntityNotFoundException("Note with id " + id + " does not exist!");

    }

    public NoteDTO saveNote(NoteDTO noteDTO) {

        Note model = noteMapper.toModel(noteDTO);
        Note saved = noteRepository.save(model);

        return noteMapper.toDto(saved);
    }

    public String deleteNote(Long id){

        if (!noteRepository.existsById(id)) {
            return "Note does not exist";
        };

        noteRepository.deleteById(id);
        return "deleted successfully";
    }


    public String deleteAllNotes() {

        noteRepository.deleteAll();
        return "Deleted all successfully";

    }




    public NoteDTO editNote(Long id, NoteDTO noteDTO) throws BadRequestException {

        if (id != noteDTO.getId()) {
            throw new BadRequestException("The note can only be changed, if the note with the id has the same id like in your update you want to do.");
        }

        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note with id " + id + " does not exist!");
        }
        Note model = noteMapper.toModel(noteDTO);
        Note saved = noteRepository.save(model);

        return noteMapper.toDto(saved);
    }
}
