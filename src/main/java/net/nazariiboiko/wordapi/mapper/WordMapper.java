package net.nazariiboiko.wordapi.mapper;

import net.nazariiboiko.wordapi.dto.WordDto;
import net.nazariiboiko.wordapi.entity.WordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WordMapper {
    WordMapper INSTANCE = Mappers.getMapper(WordMapper.class);
    WordDto mapEntityToDto(WordEntity entity);
    WordEntity mapDtoToEntity(WordDto wordDto);
}
