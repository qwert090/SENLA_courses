package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CredentialsDto;
import org.example.service.serviceInterface.CredentialsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
@RequiredArgsConstructor
public class CredentialsController {
    private final CredentialsService credentialsService;

    @PostMapping
    public void createCredentials(@RequestBody CredentialsDto credentialsDto) {
        credentialsService.createCredentials(credentialsDto);
    }

    @DeleteMapping("/{credentialsId}")
    public void deleteById(@PathVariable("credentialsId") Long credentialsId) {
        credentialsService.deleteById(credentialsId);
    }

    @PutMapping
    public void updateCredentials(@RequestBody CredentialsDto credentialsDto) {
        credentialsService.updateCredentials(credentialsDto);
    }

    @GetMapping("/{credentialsId}")
    public CredentialsDto getById(@PathVariable("credentialsId") Long credentialsId) {
        return credentialsService.getById(credentialsId);
    }
}
