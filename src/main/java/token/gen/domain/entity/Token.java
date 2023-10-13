package token.gen.domain.entity;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Token extends Entity {

    private String issuer;
    private String upn;
    private List<String> groups;
    private List<Claims> claims;

    public Token(
            String issuer,
            String upn,
            List<String> groups,
            List<Claims> claims) {
        super();
        this.issuer = issuer;
        this.upn = upn;
        this.groups = groups;
        this.claims = claims;

        validate();
    }

    protected void validate() {
        if (!isIssuerValid())
            errors.add("Issuer invalid. Must contain a valid URL host");
        if (isUpnValid())
            errors.add("Upn is mandatory and must contain a valid mail login");
        if(!isClaimsValid())
            errors.addAll(getClaimsErrors());
    }

    private boolean isIssuerValid() {
        return isValidByRegex(this.issuer, "(^http[s]?:\\/\\/)?[a-zA-Z0-9:\\/]*");
    }

    private List<String> getClaimsErrors() {
        return this.claims.stream().flatMap(claim -> claim.getErrors().stream()).collect(Collectors.toList());
    }

    private boolean isUpnValid() {
        return isValidByRegex(this.upn, "^[a-zA-Z._]+@[a-zA-Z]+.[a-zA-Z]{2}");
    }

    private boolean isClaimsValid() {
        return this.claims.stream().anyMatch(claim -> !claim.isValid());
    }

}
