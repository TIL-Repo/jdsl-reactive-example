package me.hajoo.jdslsample.controller

import me.hajoo.jdslsample.entity.Post
import me.hajoo.jdslsample.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class PostController(
    private val postService: PostService
) {
    @PostMapping("/post")
    suspend fun create(@RequestBody request: PostSpec.CreateReq): Mono<ResponseEntity<Long>> {
        return Mono.fromCompletionStage { postService.create(request) }.map { ResponseEntity.ok(it.id) }
    }

    @GetMapping("/post")
    suspend fun findAll(): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.findAll())
    }
}

class PostSpec {

    class CreateReq(
        val title: String
    )
}