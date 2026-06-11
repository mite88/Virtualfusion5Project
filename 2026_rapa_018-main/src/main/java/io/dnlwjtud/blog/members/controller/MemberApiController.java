package io.dnlwjtud.blog.members.controller;

import io.dnlwjtud.blog.members.dto.MemberDescription;
import io.dnlwjtud.blog.members.dto.MemberSaveRequest;
import io.dnlwjtud.blog.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApiController{

    private final MemberService service;

    @PostMapping
    public ResponseEntity<MemberDescription> saveMember(
         @RequestBody MemberSaveRequest request
    ){
        MemberDescription memberDescription = service.save(request);
        return ResponseEntity.ok(memberDescription);
    }

}
