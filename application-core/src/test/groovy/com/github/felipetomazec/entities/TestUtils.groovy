package com.github.felipetomazec.entities

import net.datafaker.Faker

class TestUtils {
    private static def faker = new Faker()

    static def createPost () {
        return Post.builder()
                .authorId(faker.internet().uuid())
                .content(faker.lorem().word())
                .id(faker.internet().uuid())
    }

    static def createComment() {
        return Comment.builder()
            .authorId(faker.internet().uuid())
            .content(faker.lorem().word())
            .id(faker.internet().uuid())
    }

    static def createReaction() {
        return Reaction.builder()
            .authorId(faker.internet().uuid())
            .type(Reaction.Type.LIKE)
    }
}
