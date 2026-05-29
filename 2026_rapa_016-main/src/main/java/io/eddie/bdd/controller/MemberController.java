package io.eddie.bdd.controller;

import io.eddie.bdd.dto.MemberSignupCommand;
import io.eddie.bdd.dto.MemberSignupRequest;
import io.eddie.bdd.dto.MemberSignupResponse;
import io.eddie.bdd.dto.MemberSignupResult;
import io.eddie.bdd.servivce.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberSignupResponse signup(
        @RequestBody MemberSignupRequest request
    ) {

        MemberSignupResult result = memberService.signup(
                new MemberSignupCommand(
                        request.email(),
                        request.name()
                )
        );

        return new MemberSignupResponse(
                result.memberId(),
                result.email(),
                result.name()
        );

    }

}
