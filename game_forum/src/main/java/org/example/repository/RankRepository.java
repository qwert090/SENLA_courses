package org.example.repository;

import lombok.Getter;
import org.example.entity.Rank;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class RankRepository {
    private List<Rank> ranks = new ArrayList<>();

    public void create(Rank rank){
        ranks.add(rank);
    }

    public Optional<Rank> read(long rankId){
        Optional<Rank> readRank = ranks.stream()
                .filter(id -> id.getId() == rankId)
                .findFirst();
        return readRank;
    }

    public void delete(long rankId){
        ranks = ranks.stream()
                .filter(rank -> rank.getId() != rankId)
                .toList();
    }
}
