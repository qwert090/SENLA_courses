package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomMapper {
    private final ModelMapper modelMapper;

    public <D> D toDto(Class<D> dto, Object entity){
        return modelMapper.map(entity, dto);
    }

    public <E> E toEntity(Class<E> entity, Object dto){
        return modelMapper.map(dto, entity);
    }
}
