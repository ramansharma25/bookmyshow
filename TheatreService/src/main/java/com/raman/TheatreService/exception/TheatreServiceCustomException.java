package com.raman.TheatreService.exception;

import lombok.Data;

@Data
public class TheatreServiceCustomException extends RuntimeException
{
    private String errorCode;

    public TheatreServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
