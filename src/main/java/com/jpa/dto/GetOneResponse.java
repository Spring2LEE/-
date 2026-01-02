package com.jpa.dto;

import lombok.Getter;

@Getter
public class GetOneResponse {

    private final long id;
    private final String title;
    private final String content;
    private final String name;
    private final String password;


    public GetOneResponse(long id, String title, String content, String name, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }
}
