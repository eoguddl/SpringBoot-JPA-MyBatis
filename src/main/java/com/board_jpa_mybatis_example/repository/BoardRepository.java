package com.board_jpa_mybatis_example.repository;

import com.board_jpa_mybatis_example.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
