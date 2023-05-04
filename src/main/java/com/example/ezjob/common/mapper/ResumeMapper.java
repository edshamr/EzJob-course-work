package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
  Resume toResume(ResumeRequestDto resumeRequest);
  Resume toResume(RegistrationRequestDto registrationRequest);
  ResumeResponseDto toResumeResponseDto(Resume resume);
  ResumeRequestDto toResumeRequestDto(RegistrationRequestDto registrationRequest);
  void updateResume(@MappingTarget Resume resumeToUpdate, Resume resume);
}
