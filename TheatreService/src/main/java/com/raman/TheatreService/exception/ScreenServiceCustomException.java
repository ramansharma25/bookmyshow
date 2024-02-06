package com.raman.TheatreService.exception;

import lombok.Data;

@Data
public class ScreenServiceCustomException extends RuntimeException
{
    private String errorCode;

    public ScreenServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
