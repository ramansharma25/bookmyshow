package com.raman.TheatreService.exception;

import lombok.Data;

@Data
public class ShowServiceCustomException extends RuntimeException
{
    private String errorCode;

    public ShowServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
