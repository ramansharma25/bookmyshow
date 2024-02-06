package com.raman.TheatreService.exception;

import lombok.Data;

@Data
public class TicketServiceCustomException extends RuntimeException
{
    private String errorCode;

    public TicketServiceCustomException(String message, String errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
}
