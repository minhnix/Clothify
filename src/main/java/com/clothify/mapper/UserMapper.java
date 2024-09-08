package com.clothify.mapper;

import com.clothify.domain.user.User;
import com.clothify.security.jwt.JwtPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", source = "source.userId")
    User toUser(JwtPayload source);
}
