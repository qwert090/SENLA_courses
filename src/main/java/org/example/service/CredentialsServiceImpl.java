package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CredentialsDto;
import org.example.entity.Credentials;
import org.example.exception.entityNotFound.CredentialsNotFoundException;
import org.example.repository.impl.CredentialsRepository;
import org.example.service.serviceInterface.CredentialsService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {
    private final CredentialsRepository credentialsRepository;
    private final CustomMapper mapper;

    @Override
    public void createCredentials(CredentialsDto credentialsDto) {
        Credentials credentials = mapper.toEntity(Credentials.class, credentialsDto);
        credentialsRepository.save(credentials);
    }

    @Override
    public void deleteById(Long id) {
        credentialsRepository.deleteById(id);
    }

    @Override
    public CredentialsDto getById(Long id) {
        Credentials credentials = credentialsRepository.findById(id).orElseThrow(() ->
                new CredentialsNotFoundException("id " + id));
        return mapper.toDto(CredentialsDto.class, credentials);
    }

    @Override
    public void updateCredentials(CredentialsDto credentialsDto) {
        Credentials updatedCredentials = mapper.toEntity(Credentials.class, credentialsDto);
        credentialsRepository.update(updatedCredentials);
    }
}
