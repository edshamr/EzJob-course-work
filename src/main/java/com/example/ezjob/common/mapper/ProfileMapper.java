package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.CompanyProfileResponseDto;
import com.example.ezjob.model.dto.UserProfileResponseDto;
import com.example.ezjob.persistense.entity.Company;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    UserProfileResponseDto toProfileResponseDto(@Nonnull String username, @Nonnull Resume resume);
    CompanyProfileResponseDto toProfileResponseDto(@Nonnull String username, @Nonnull Company company);
}
