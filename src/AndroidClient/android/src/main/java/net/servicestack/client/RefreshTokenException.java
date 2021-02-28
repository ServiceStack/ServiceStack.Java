package net.servicestack.client;

public class RefreshTokenException extends RuntimeException {
    public RefreshTokenException(WebServiceException innerException) {
        super(innerException.getMessage(), innerException);
    }
}
