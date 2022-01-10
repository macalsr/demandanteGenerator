package com.nexty.service.mapper;

import com.nexty.domain.Demandante;
import com.nexty.service.dto.DemandanteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Demandante} and its DTO {@link DemandanteDTO}.
 */
@Mapper(componentModel = "spring", uses = { TelefoneMapper.class, EnderecoMapper.class })
public interface DemandanteMapper extends EntityMapper<DemandanteDTO, Demandante> {
    @Mapping(target = "telefone", source = "telefone", qualifiedByName = "id")
    @Mapping(target = "endereco", source = "endereco", qualifiedByName = "id")
    DemandanteDTO toDto(Demandante s);
}
