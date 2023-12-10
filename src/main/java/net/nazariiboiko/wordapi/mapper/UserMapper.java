package net.nazariiboiko.wordapi.mapper;

import net.nazariiboiko.wordapi.dto.UserDto;
import net.nazariiboiko.wordapi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto mapEntityToDto(UserEntity userEntity);
    UserEntity mapDtoToEntity(UserDto userDto);
}
