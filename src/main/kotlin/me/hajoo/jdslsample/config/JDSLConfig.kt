package me.hajoo.jdslsample.config

import com.linecorp.kotlinjdsl.query.creator.SubqueryCreator
import com.linecorp.kotlinjdsl.query.creator.SubqueryCreatorImpl
import com.linecorp.kotlinjdsl.spring.data.reactive.query.SpringDataHibernateMutinyReactiveQueryFactory
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.hibernate.reactive.mutiny.Mutiny.SessionFactory
import org.hibernate.reactive.session.ReactiveSession
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ReactiveSession::class)
class JDSLConfig {
    @Bean
    @ConditionalOnMissingBean(SubqueryCreator::class)
    fun subQueryCreator(): SubqueryCreator {
        return SubqueryCreatorImpl()
    }

    @Bean
    fun entityManagerFactory(): EntityManagerFactory = Persistence.createEntityManagerFactory("factory")


    @Bean
    fun mutinySessionFactory(entityManagerFactory: EntityManagerFactory): SessionFactory =
        entityManagerFactory.unwrap(SessionFactory::class.java)

    @Bean
    fun queryFactory(sessionFactory: SessionFactory, subQueryCreator: SubqueryCreator): SpringDataHibernateMutinyReactiveQueryFactory {
        return SpringDataHibernateMutinyReactiveQueryFactory(
            sessionFactory = sessionFactory,
            subqueryCreator = subQueryCreator
        )
    }
}