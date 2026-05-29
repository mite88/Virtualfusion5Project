package io.eddie.bdd.servivce;

import io.eddie.bdd.dto.MemberSignupCommand;
import io.eddie.bdd.dto.MemberSignupResult;

public interface MemberService {
    MemberSignupResult signup(MemberSignupCommand command);
}
