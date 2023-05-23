package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
  Resume toResume(@Nonnull ResumeRequestDto resumeRequest);
  @Mapping(source = "id", target = "resumeId")
  ResumeResponseDto toResumeResponseDto(@Nonnull Resume resume);
  @Mapping(target = "authUser", ignore = true)
  void updateResume(@MappingTarget @Nonnull Resume destination, @Nonnull Resume source);
}
