package com.mv.ct.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorMessageDto {

    private String code;
    private String message;

}
