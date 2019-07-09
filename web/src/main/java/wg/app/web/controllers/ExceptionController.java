package wg.app.web.controllers;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wg.app.model.exceptions.ExceptionInfo;
import wg.app.model.exceptions.Info;
import wg.app.model.exceptions.MyException;

@RestControllerAdvice
public class ExceptionController
{
    @ExceptionHandler({MyException.class})
    public Info myExceptionHandler(MyException e) {
        return Info.builder().exception(e.getExceptionInfo()).data(null).build();
    }

    @ExceptionHandler(Exception.class)
    public Info exceptionHandler(Exception e) {
        return Info.builder().exception(new ExceptionInfo("UNIDENTIFIED EXCEPTION")).data(null).build();
    }


}
