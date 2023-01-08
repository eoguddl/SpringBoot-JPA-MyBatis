package com.board_jpa_mybatis_example.service.mybatis;

import com.board_jpa_mybatis_example.entity.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMyBatisMapper {

    @Select("SELECT * FROM Board WHERE ID = #{id}")
    Board getBoardById(@Param("id") Long id);

    @Select("SELECT * FROM Board")
    List<Board> getBoardAll();

    @Insert("INSERT INTO Board(id, title, content, created_at, edit_at) " +
            "VALUES(#{Board.id}, #{Board.title}, #{Board.content}, #{Board.createdAt}, #{Board.editAt})")
    void createBoard(@Param("Board") final Board board);

    @Update("UPDATE Board SET title = #{Board.title}, content = #{Board.content}, edit_at = #{Board.editAt}")
    void updateBoard(@Param("Board") final Board board);

    @Delete("DELETE FROM Board WHERE ID = #{id}")
    void deleteBoard(Long id);

}
