package com.as.users.mapper;

import com.as.users.entity.User;
import com.as.users.request.UserRm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    @Mapping(target = "registrationDate", source = "registrationDate",dateFormat = "yyyy-MM-dd HH:mm:ss.SSS")
    User toUser(UserRm rm);
    default LocalDate mapStringToLocalDate(String date) {
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}