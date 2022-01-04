package com.next.demandanteGenerator.utility;

import com.next.demandanteGenerator.model.Sexo;
import jdk.jfr.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;


@Converter(autoApply = true)
public class EnumConverter implements AttributeConverter<Sexo, String> {

    @Override
    public String convertToDatabaseColumn(Sexo sexo) {
        if(sexo == null){
            return null;
        }
        return sexo.getSexo();
    }

    @Override
    public Sexo convertToEntityAttribute(String sexo) {
        if(sexo == null){
            return null;
        }
        return Stream.of(Sexo.values())
                .filter(s -> s.getSexo().equals(sexo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
