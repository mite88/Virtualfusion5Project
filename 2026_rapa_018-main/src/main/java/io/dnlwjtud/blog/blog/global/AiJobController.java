package io.dnlwjtud.blog.blog.global;

import io.dnlwjtud.blog.blog.global.model.AiJob;
import io.dnlwjtud.blog.blog.global.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * packageName    : io.dnlwjtud.blog.blog.global
 * fileName       : AiJobController
 * author         : Admin
 * date           : 26. 6. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 12.        Admin       최초 생성
 */
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiJobController {

    private final JobService jobService;

    // ① Job 제출
    @PostMapping("/jobs")
    public ResponseEntity<Map<String, String>> submit(
            @RequestBody Map<String, String> body) {

        String input = body.get("input");
        if (input == null || input.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "input is required"));
        }

        String jobId = jobService.submitJob(input);
        return ResponseEntity.accepted()
                .body(Map.of("jobId", jobId));
    }

    // ② Job 상태/결과 조회 (클라이언트 Polling)
    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<AiJob> getJob(@PathVariable String jobId) {
        AiJob job = jobService.getJob(jobId);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }
}