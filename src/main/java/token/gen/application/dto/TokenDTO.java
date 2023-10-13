package token.gen.application.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDTO {

    private String requesterHost;
    private String login;
    private List<String> roles;
    private List<ClaimsDTO> claims;

    public TokenDTO(String login, List<String> roles, List<ClaimsDTO> claims) {
        this.login = login;
        this.roles = roles;
        this.claims = claims;
    }

    public void addHost(String requesterHost) {
        this.requesterHost = requesterHost;
    }
}
