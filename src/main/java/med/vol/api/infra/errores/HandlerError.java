package med.vol.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class HandlerError {

    private record ErrorResponse(String campo, String error){
        public ErrorResponse(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity handlerError$00(MethodArgumentNotValidException e){

        return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map(ErrorResponse::new).collect(Collectors.toList()));
    }
}
