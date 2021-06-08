package cn.groupofseven.questionaresystem.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionType {
    PARAM_ERROR(HttpStatus.BAD_REQUEST), UNAUTHORIZED(HttpStatus.UNAUTHORIZED), NOT_FOUND(HttpStatus.NOT_FOUND), SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    public HttpStatus getStatus(){
        return status;
    }

    ExceptionType(HttpStatus status){
        this.status = status;
    }
}
