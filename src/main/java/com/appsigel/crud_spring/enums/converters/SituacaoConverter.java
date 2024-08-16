package com.appsigel.crud_spring.enums.converters;

import com.appsigel.crud_spring.enums.Situacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)

public class SituacaoConverter implements AttributeConverter<Situacao, String> {
    @Override
    public String convertToDatabaseColumn(Situacao situacao) {
    if(situacao == null){
        return null;
    }
    return situacao.getValue();
    
}
    @Override
    public Situacao convertToEntityAttribute(String value) {
      if(value == null){
          return null;
    }
    return Stream.of(Situacao.values())
            .filter(c -> c.getValue().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }   
}

