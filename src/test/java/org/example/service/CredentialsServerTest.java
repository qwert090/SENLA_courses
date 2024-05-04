package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.CredentialsDto;
import org.example.entity.Credentials;
import org.example.repository.impl.CredentialsRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class CredentialsServerTest {

    @Mock
    private CredentialsRepository credentialsRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private CredentialsServiceImpl credentialsService;

    @Test
    public void createCredentialsTest() {
        Credentials credentials = new Credentials();
        CredentialsDto credentialsDto = new CredentialsDto();
        when(mapper.toEntity(Credentials.class, credentialsDto)).thenReturn(credentials);
        credentialsService.createCredentials(credentialsDto);
        verify(credentialsRepository, times(1)).save(credentials);
    }

    @Test
    public void updateCredentialsTest() {
        Credentials credentials = new Credentials();
        CredentialsDto credentialsDto = new CredentialsDto();
        when(mapper.toEntity(Credentials.class, credentialsDto)).thenReturn(credentials);
        credentialsService.updateCredentials(credentialsDto);
        verify(credentialsRepository, times(1)).update(credentials);
    }

    @Test
    public void getByIdTest() {
        long credentialsId = 1L;
        Credentials credentials = new Credentials();
        CredentialsDto credentialsDto = new CredentialsDto();
        when(mapper.toDto(CredentialsDto.class, credentials)).thenReturn(credentialsDto);
        when(credentialsRepository.findById(credentialsId)).thenReturn(credentials);
        CredentialsDto result = credentialsService.getById(credentialsId);
        assertSame(credentialsDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long credentialsId = 1L;
        credentialsService.deleteById(credentialsId);
        verify(credentialsRepository, times(1)).deleteById(credentialsId);
    }
}
