package token.gen.application.dto;

import lombok.Getter;

@Getter
public class PayloadDTO {
    private String type;
    private String payload;

    public PayloadDTO(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }
}
