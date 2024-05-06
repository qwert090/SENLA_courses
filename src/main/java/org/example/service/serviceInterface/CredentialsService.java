package org.example.service.serviceInterface;

import org.example.dto.CredentialsDto;

public interface CredentialsService {
    void createCredentials(CredentialsDto credentialsDto);

    void deleteById(Long id);

    CredentialsDto getById(Long id);

    void updateCredentials(CredentialsDto credentialsDto);
}
