package token.gen.domain.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessException extends Exception {
    
    private List<String> errors;

    public BusinessException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}
