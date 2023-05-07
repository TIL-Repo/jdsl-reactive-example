package me.hajoo.jdslsample.service

import com.linecorp.kotlinjdsl.spring.data.reactive.query.SpringDataHibernateMutinyReactiveQueryFactory
import com.linecorp.kotlinjdsl.spring.data.reactive.query.listQuery
import me.hajoo.jdslsample.controller.PostSpec
import me.hajoo.jdslsample.entity.Post
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.stereotype.Service
import java.util.concurrent.CompletionStage

@Service
class PostService(
    private val mutinySessionFactory: Mutiny.SessionFactory,
    private val reactiveFactory: SpringDataHibernateMutinyReactiveQueryFactory
) {

    fun create(spec: PostSpec.CreateReq): CompletionStage<Post> {
        val post = Post(title = spec.title)
        return mutinySessionFactory.withSession {
            session -> session.persist(post)
            .flatMap { session.flush() } }
            .map { post }
            .subscribeAsCompletionStage()
    }

    suspend fun findAll(): List<Post> {
        return reactiveFactory.listQuery {
            select(entity(Post::class))
            from(entity(Post::class))
        }
    }
}



