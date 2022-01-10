package com.nexty.service.mapper;

import com.nexty.domain.Telefone;
import com.nexty.service.dto.TelefoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Telefone} and its DTO {@link TelefoneDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TelefoneMapper extends EntityMapper<TelefoneDTO, Telefone> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TelefoneDTO toDtoId(Telefone telefone);
}
