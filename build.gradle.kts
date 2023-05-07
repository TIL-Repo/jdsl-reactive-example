import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
}

group = "me.hajoo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
        exclude("org.hibernate.orm", "hibernate-core")
    }
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java:8.0.33")

    // reactive
    // https://mvnrepository.com/artifact/org.hibernate.reactive/hibernate-reactive-core-jakarta/1.1.9.Final
    implementation("org.hibernate.reactive:hibernate-reactive-core-jakarta:1.1.9.Final")
    // https://mvnrepository.com/artifact/com.linecorp.kotlin-jdsl/spring-data-kotlin-jdsl-hibernate-reactive-jakarta/2.2.1.RELEASE
    implementation("com.linecorp.kotlin-jdsl:spring-data-kotlin-jdsl-hibernate-reactive-jakarta:2.2.1.RELEASE")
    // https://mvnrepository.com/artifact/io.vertx/vertx-mysql-client
    implementation("io.vertx:vertx-mysql-client:4.4.1")

    // mac os
    // https://mvnrepository.com/artifact/io.netty/netty-resolver-dns-native-macos/4.1.92.Final
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.92.Final:osx-aarch_64")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
