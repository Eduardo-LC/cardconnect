package com.koerich.cardconnect.repository;

import com.koerich.cardconnect.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByGameId(String gameId);
}

