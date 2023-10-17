package token.gen.application.useCase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import token.gen.application.dto.PayloadDTO;
import token.gen.application.dto.TokenDTO;
import token.gen.application.response.APIResponse;
import token.gen.domain.entity.Token;
import token.gen.domain.exception.BusinessException;
import token.gen.domain.port.IConvert;

@ApplicationScoped
public class BuildTokenJWT {

    @Inject
    IConvert<TokenDTO, Token> convertToDomain;

    public APIResponse build(TokenDTO dto) {

        try {

            Token token = convertToDomain.convert(dto);

            JwtClaimsBuilder jwt = Jwt.issuer(token.getIssuer())
            .upn(token.getUpn())
            .groups(new HashSet<>(token.getGroups()));

            token.getClaims().stream().forEach(claim -> jwt.claim(claim.getKey(),
            claim.getValue()));

            String tkn = jwt.innerSign().encrypt();

            PayloadDTO payload = new PayloadDTO("JWT", tkn);

            return new APIResponse<PayloadDTO>(200, List.of(payload));

        } catch (BusinessException e) {
            return new APIResponse<String>(400, e.getErrors());
        }
        catch (Exception e) {
            return new APIResponse<String>(400, List.of(e.getMessage()));
        }
    }
}
