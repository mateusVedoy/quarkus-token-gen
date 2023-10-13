package token.gen.application.response;

import java.util.List;

import lombok.Getter;

@Getter
public class APIResponse<T> {
    private int status;
    private List<T> result;

    public APIResponse(int status, List<T> result) {
        this.status = status;
        this.result = result;
    }
}
