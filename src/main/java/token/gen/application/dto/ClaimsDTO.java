package token.gen.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClaimsDTO {
    private String key;
    private String value;

    public ClaimsDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
