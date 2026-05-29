package io.eddie.bdd.servivce;

import io.eddie.bdd.dto.MemberSignupCommand;
import io.eddie.bdd.dto.MemberSignupResult;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public MemberSignupResult signup(MemberSignupCommand command) {
        return new MemberSignupResult(1L, command.name(), command.email());
    }

}
