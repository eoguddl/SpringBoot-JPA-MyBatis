package com.board_jpa_mybatis_example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {

    private Long id;

    private String title;

    private String content;

}
