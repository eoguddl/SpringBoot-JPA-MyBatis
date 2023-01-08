package com.board_jpa_mybatis_example.controller;

import com.board_jpa_mybatis_example.dto.BoardDto;
import com.board_jpa_mybatis_example.entity.Board;
import com.board_jpa_mybatis_example.service.jpa.BoardJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/board")
public class BoardJPAController {

    @Autowired
    private BoardJPAService boardJPAService;

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardJPAService.getBoardById(id);
    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoardAll() {
        return boardJPAService.getBoardAll();
    }

    @PostMapping
    ResponseEntity<Board> createAndUpdateBoard(@RequestBody BoardDto boardDto) {
        return boardJPAService.createAndUpdateBoard(boardDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deleteBoard(@PathVariable Long id) {
        return boardJPAService.deleteBoard(id);
    }


}
