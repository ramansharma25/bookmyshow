package com.raman.TheatreService.exception;

import lombok.Data;

@Data
public class MovieServiceCustomException extends RuntimeException
{
    private String errorCode;

    public MovieServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
