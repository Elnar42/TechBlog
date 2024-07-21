package com.techblog.blogtech.exceptions;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class NoDataFound extends RuntimeException {

    private LocalDateTime time;

    public NoDataFound(){
        super();
    }
    public NoDataFound(String message, LocalDateTime time){
        super(message);
        this.time = time;
    }

    public NoDataFound(String message, Throwable cause){
        super(message, cause);
    }


}


