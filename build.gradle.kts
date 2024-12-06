import java.net.URI

plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    signing
}

version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

publishing {
    publications {
        withType(MavenPublication::class) {
            groupId ="io.github.dmzz-yyhyy"
            artifactId = "potato-epub"
            from(components["java"])
            pom {
                name.set("potato-epub")
                description.set("a kotlin epub lib")
                url.set("https://github.com/dmzz-yyhyy/PotatoEpub")
                inceptionYear.set("2024")
                developers {
                    developer {
                        id.set("nightfish")
                        name.set("NightFish")
                        email.set("hk198580666@outlook.com")
                    }
                }
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/dmzz-yyhyy/PotatoEpub")
                        distribution.set("repo")
                    }
                }
                scm {
                    url.set("https://github.com/dmzz-yyhyy/PotatoEpub")
                    connection.set("scm:git:git@github.com:dmzz-yyhyy/PotatoEpub.git")
                    developerConnection.set("scm:git:ssh:git@github.com:dmzz-yyhyy/PotatoEpub.git")
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
            // 根据版本名中是否以"SNAPSHOT"为结尾，则上传到"SNAPSHOT"仓库，通常作为开发测试使用，反之则正式版本使用
            if (project.version.toString().endsWith("-SNAPSHOT")) {
                url = URI("https://s01.oss.sonatype.org/content/repositories/snapshots")
            } else {
                url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
        }
    }
}

signing {
    sign(publishing.publications)
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}