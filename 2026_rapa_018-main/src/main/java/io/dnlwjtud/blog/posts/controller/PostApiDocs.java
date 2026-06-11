package io.dnlwjtud.blog.posts.controller;

import io.dnlwjtud.blog.posts.dto.EditPostRequest;
import io.dnlwjtud.blog.posts.dto.PostDescription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * packageName    : io.dnlwjtud.blog.posts.controller
 * fileName       : PostApiDocs
 * author         : Admin
 * date           : 26. 6. 11.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 11.        Admin       최초 생성
 */
public interface PostApiDocs {


    @Operation(
            summary = "게시글 작성",
            description = "새로운 게시글을 작성합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "201",
            description = "작성된 게시물에 대한 정보",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = PostDescription.class
                            )
                    )
            }
    )
    public ResponseEntity<PostDescription> savePost(
            @RequestBody EditPostRequest request,
            Principal principal
    );


    @Operation(
            summary = "게시글 단건 조회",
            description = "게시글 ID를 이용해 게시글 하나를 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "조회된 게시물에 대한 정보",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = PostDescription.class
                            )
                    )
            }
    )
    @ApiResponse(
            responseCode = "404",
            description = "게시글을 찾을 수 없음",
            content = @Content
    )
    public ResponseEntity<PostDescription> findById(@PathVariable Long id);


    @Operation(
            summary = "게시글 전체 조회",
            description = "등록된 모든 게시글 목록을 조회합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "게시글 목록",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = PostDescription.class
                                    )
                            )
                    )
            }
    )
    public ResponseEntity<List<PostDescription>> findAll();


    @Operation(
            summary = "게시글 수정",
            description = "게시글 ID를 이용해 게시글 내용을 수정합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "수정된 게시물에 대한 정보",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = PostDescription.class
                            )
                    )
            }
    )
    @ApiResponse(
            responseCode = "401",
            description = "인증되지 않은 사용자",
            content = @Content
    )
    @ApiResponse(
            responseCode = "403",
            description = "게시글 수정 권한 없음",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "게시글을 찾을 수 없음",
            content = @Content
    )
    public ResponseEntity<PostDescription> updatePost(
            @RequestBody EditPostRequest request,
            @PathVariable Long id,
            Principal principal
    );


    @Operation(
            summary = "게시글 삭제",
            description = "게시글 ID를 이용해 게시글을 삭제합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "204",
            description = "게시글 삭제 성공",
            content = @Content
    )
    @ApiResponse(
            responseCode = "401",
            description = "인증되지 않은 사용자",
            content = @Content
    )
    @ApiResponse(
            responseCode = "403",
            description = "게시글 삭제 권한 없음",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "게시글을 찾을 수 없음",
            content = @Content
    )
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id
    );


}