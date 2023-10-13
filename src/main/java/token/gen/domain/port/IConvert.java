package token.gen.domain.port;

import token.gen.domain.exception.BusinessException;

public interface IConvert<T1, T2> {
    T2 convert(T1 entry) throws BusinessException;
}
