package me.hajoo.jdslsample.service

import com.linecorp.kotlinjdsl.spring.data.reactive.query.SpringDataHibernateMutinyReactiveQueryFactory
import org.springframework.stereotype.Service

@Service
class PostService(
    private val reactiveFactory: SpringDataHibernateMutinyReactiveQueryFactory
) {
}



