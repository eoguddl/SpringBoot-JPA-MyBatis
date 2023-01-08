package com.board_jpa_mybatis_example.service.jpa;

import com.board_jpa_mybatis_example.repository.BoardRepository;
import com.board_jpa_mybatis_example.dto.BoardDto;
import com.board_jpa_mybatis_example.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardJPAServiceImpl implements BoardJPAService {

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public ResponseEntity<Board> getBoardById(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if (board.isPresent()) {
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Board>> getBoardAll() {
        List<Board> boardList = boardRepository.findAll();

        if (boardList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Board> createAndUpdateBoard(BoardDto boardDto) {
        try {
            Board board;

            if (boardDto.getId() == null) { // create
                board = Board.builder()
                        .id(null)
                        .title(boardDto.getTitle())
                        .content(boardDto.getContent())
                        .createdAt(LocalDateTime.now())
                        .editAt(null)
                        .build();
            } else { // update
                Optional<Board> boardOpt = boardRepository.findById(boardDto.getId());

                if (boardOpt.isPresent()) {
                    board = boardOpt.get();

                    board.setTitle(boardDto.getTitle());
                    board.setContent(boardDto.getContent());
                    board.setEditAt(LocalDateTime.now());
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
            boardRepository.save(board);

            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception exception) {
            log.info("error: {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);

        if (board.isPresent()) {
            boardRepository.delete(board.get());

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
