package com.nexty.service.mapper;

import com.nexty.domain.Endereco;
import com.nexty.service.dto.EnderecoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Endereco} and its DTO {@link EnderecoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EnderecoDTO toDtoId(Endereco endereco);
}
