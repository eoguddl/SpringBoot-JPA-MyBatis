package com.board_jpa_mybatis_example.controller;

import com.board_jpa_mybatis_example.dto.BoardDto;
import com.board_jpa_mybatis_example.entity.Board;
import com.board_jpa_mybatis_example.service.mybatis.BoardMyBatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mybatis/board")
public class BoardMyBatisController {

    @Autowired
    private BoardMyBatisService boardMyBatisService;

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardMyBatisService.getBoardById(id);
    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoardAll() {
        return boardMyBatisService.getBoardAll();
    }

    @PostMapping
    ResponseEntity<Board> createBoard(@RequestBody BoardDto boardDto) {
        return boardMyBatisService.createBoard(boardDto);
    }

    @PutMapping
    ResponseEntity<HttpStatus> updateBoard(@RequestBody BoardDto boardDto) {
        return boardMyBatisService.updateBoard(boardDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteBoard(@PathVariable Long id) {
        return boardMyBatisService.deleteBoard(id);
    }

}
