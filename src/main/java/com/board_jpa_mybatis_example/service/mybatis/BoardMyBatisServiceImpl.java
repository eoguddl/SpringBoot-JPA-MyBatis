package com.board_jpa_mybatis_example.service.mybatis;

import com.board_jpa_mybatis_example.dto.BoardDto;
import com.board_jpa_mybatis_example.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BoardMyBatisServiceImpl implements BoardMyBatisService {

    @Autowired
    private BoardMyBatisMapper boardMyBatisMapper;

    @Override
    public ResponseEntity<Board> getBoardById(Long id) {
        Board board = boardMyBatisMapper.getBoardById(id);

        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Board>> getBoardAll() {
        List<Board> boardList = boardMyBatisMapper.getBoardAll();

        if (!boardList.isEmpty()) {
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<Board> createBoard(BoardDto boardDto) {
        try {
            Board board = Board.builder()
                    .id(null)
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .createdAt(LocalDateTime.now())
                    .editAt(null)
                    .build();

            boardMyBatisMapper.createBoard(board);

            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> updateBoard(BoardDto boardDto) {
        try {
            Long boardId = boardDto.getId();
            Board board = boardMyBatisMapper.getBoardById(boardId);

            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setEditAt(LocalDateTime.now());

            boardMyBatisMapper.updateBoard(board);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteBoard(Long id) {
        try {
            boardMyBatisMapper.deleteBoard(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
