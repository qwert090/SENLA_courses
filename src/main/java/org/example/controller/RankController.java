package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RankDto;
import org.example.service.serviceInterface.RankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ranks")
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;

    @PostMapping
    public void createRank(@RequestBody RankDto rankDto){
        rankService.createRank(rankDto);
    }

    @DeleteMapping("/{rankId}")
    public void deleteById(@PathVariable("rankId") Long rankId){
        rankService.deleteById(rankId);
    }

    @PutMapping
    public void updateRank(@RequestBody RankDto rankDto){
        rankService.updateRank(rankDto);
    }

    @GetMapping("/{rankId}")
    public RankDto getById(@PathVariable("rankId") Long rankId){
        return rankService.getById(rankId);
    }
}
