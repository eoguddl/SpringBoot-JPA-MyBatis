package com.board_jpa_mybatis_example.service.mybatis;

import com.board_jpa_mybatis_example.dto.BoardDto;
import com.board_jpa_mybatis_example.entity.Board;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BoardMyBatisService {


    ResponseEntity<Board> getBoardById(Long id);

    ResponseEntity<List<Board>> getBoardAll();

    ResponseEntity<Board> createBoard(BoardDto boardDto);

    ResponseEntity<HttpStatus> updateBoard(BoardDto boardDto);

    ResponseEntity<HttpStatus> deleteBoard(Long id);

}
