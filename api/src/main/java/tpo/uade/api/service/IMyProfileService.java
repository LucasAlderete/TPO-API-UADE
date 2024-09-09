package tpo.uade.api.service;

import tpo.uade.api.dto.MyProfileDto;

public interface IMyProfileService {
    MyProfileDto getMyProfileDtoById(Long id);
}
