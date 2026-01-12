package com.jpa.dto;

import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Getter
public class UpdateResponse {

    private final long id;
    private final String title;
    private final String content;
    private final String name;
 

    public UpdateResponse(long id, String title, String content, String name, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        
    }
}
