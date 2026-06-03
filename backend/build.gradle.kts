plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.0.21"
    application
}

group = "tech.tabrita"
version = "0.1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("tech.tabrita.backend.ApplicationKt")
}

dependencies {
    // Ktor
    val ktorVersion = "3.0.1"
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")

    // JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // Password hashing (BCrypt)
    implementation("at.favre.lib:bcrypt:0.10.2")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.6")

    // Testing (optional for now)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
}