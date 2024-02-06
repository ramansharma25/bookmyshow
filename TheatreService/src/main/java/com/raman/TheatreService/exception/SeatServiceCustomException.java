package com.raman.TheatreService.exception;

import lombok.Data;

@Data
public class SeatServiceCustomException extends RuntimeException
{
    private String errorCode;

    public SeatServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
