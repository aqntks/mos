package com.moskhu.domain.posts;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<Collection<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(Collection<String> stringList) {
        return String.join(SPLIT_CHAR, stringList);
    }

    @Override
    public Collection<String> convertToEntityAttribute(String string) {
        return Arrays.asList(string.split(SPLIT_CHAR));
    }
}