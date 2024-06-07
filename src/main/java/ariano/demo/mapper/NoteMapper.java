package ariano.demo.mapper;

import ariano.demo.model.Note;
import ariano.demo.service.dto.NoteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoteMapper {

    NoteDTO toDto(Note note);

    Note toModel(NoteDTO dto);


}
