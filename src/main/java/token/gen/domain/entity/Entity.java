package token.gen.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

@Getter
public abstract class Entity {

    public List<String> errors;

    public Entity() {
        this.errors = new ArrayList<>();
    }

    protected abstract void validate();

    public boolean isValid() {
        return errors.size() == 0;
    };

    protected <T> boolean isNull(T value) {
        return value == null;
    }

    protected boolean isValidByRegex(String prop, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(prop);
        return matcher.matches();
    }
}
