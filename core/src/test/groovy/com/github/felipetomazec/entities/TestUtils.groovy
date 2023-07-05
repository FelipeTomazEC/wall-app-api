package com.github.felipetomazec.entities

import net.datafaker.Faker

class TestUtils {
    private static def faker = new Faker()

    static def createPost () {
        return Post.builder()
                .authorId(UUID.randomUUID())
                .content(faker.lorem().word())
                .id(UUID.randomUUID())
    }

    static def createComment() {
        return Comment.builder()
            .authorId(UUID.randomUUID())
            .content(faker.lorem().word())
            .id(UUID.randomUUID())
    }

    static def createReaction() {
        return Reaction.builder()
            .authorId(UUID.randomUUID())
            .type(Reaction.Type.LIKE)
    }
}
