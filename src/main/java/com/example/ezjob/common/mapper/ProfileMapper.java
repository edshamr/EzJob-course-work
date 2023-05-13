package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.ProfileResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileResponseDto toProfileResponseDto(@Nonnull String username, @Nonnull Resume resume);
}
