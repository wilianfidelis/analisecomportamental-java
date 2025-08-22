package com.tecnodata.loja.entities.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToCharConverter implements AttributeConverter<Boolean, String> {

    private static final String CHAR_NAO = "N";

    private static final String CHAR_SIM = "S";

    @Override
    public String convertToDatabaseColumn(Boolean value){
      String retorno = null;
      if(Boolean.TRUE.equals(value)) retorno = CHAR_SIM;
      else if(Boolean.FALSE.equals(value)) retorno = CHAR_NAO;
      return  retorno;
    }

    @Override
    public Boolean convertToEntityAttribute(String value){
        Boolean retorno = null;
        if(CHAR_SIM.equalsIgnoreCase(value)) retorno = Boolean.TRUE;
        else if(CHAR_NAO.equalsIgnoreCase(value)) retorno = Boolean.FALSE;
        return  retorno;
    }
}
