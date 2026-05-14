package io.eddie.flawydemo.domain.repository;

import io.eddie.flawydemo.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}