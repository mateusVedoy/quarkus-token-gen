package token.gen.domain.entity;

import lombok.Getter;

@Getter
public class Claims extends Entity {
    private String key;
    private String value;

    public Claims(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    protected void validate() {
        if(isNull(key))
            errors.add("Key is mandatory and cannot be null");
        if(isNull(value))
            errors.add("Value is mandatory and cannot be null");
    }
}
