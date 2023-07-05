package com.github.felipetomazec.utils

import com.github.felipetomazec.entities.Author
import com.github.felipetomazec.entities.Comment
import com.github.felipetomazec.entities.Post
import com.github.felipetomazec.entities.Reaction
import net.datafaker.Faker

import java.time.LocalDateTime

class TestUtils {
    private def static faker = new Faker()

    static def createPost() {
        return Post.builder()
            .authorId(UUID.randomUUID())
            .id(UUID.randomUUID())
            .reactions(new HashSet<Reaction>())
            .content(faker.lorem().word())
            .createdAt(LocalDateTime.now())
    }

    static def createComment() {
        return Comment.builder()
            .id(UUID.randomUUID())
            .authorId(UUID.randomUUID())
            .content(faker.lorem().word())
            .reactions(new HashSet<Reaction>())
            .createdAt(LocalDateTime.now())
    }

    static def createReaction() {
        def reactionsTypes = Reaction.Type.values()
        def randomReactionIndex = new Random()
                .nextInt(reactionsTypes.size())

        return Reaction.builder()
            .authorId(UUID.randomUUID())
            .type(reactionsTypes[randomReactionIndex])
    }

    static def createAuthor() {
        return Author.builder()
            .id(UUID.randomUUID())
            .email(faker.internet().emailAddress())
            .profileImage(faker.internet().image())
            .username(faker.name().username())
    }
}
