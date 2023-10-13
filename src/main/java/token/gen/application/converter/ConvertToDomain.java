package token.gen.application.converter;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import token.gen.application.dto.ClaimsDTO;
import token.gen.application.dto.TokenDTO;
import token.gen.domain.entity.Claims;
import token.gen.domain.entity.Token;
import token.gen.domain.exception.BusinessException;
import token.gen.domain.port.IConvert;

@ApplicationScoped
public class ConvertToDomain implements IConvert<TokenDTO, Token> {

    @Override
    public Token convert(TokenDTO entry) throws BusinessException {
        List<Claims> claims = buildClaims(entry.getClaims());
        Token token = new Token(entry.getRequesterHost(), entry.getLogin(), entry.getRoles(), claims);
        if(token.isValid())
            return token;
        throw new BusinessException("There's errors into token creation", token.getErrors());
    }
    
    private List<Claims> buildClaims(List<ClaimsDTO> dto) {
        List<Claims> claims = new ArrayList<>();
        dto.forEach(claim -> {
            claims.add(new Claims(claim.getKey(), claim.getValue()));
        });

        return claims;
    }
}
