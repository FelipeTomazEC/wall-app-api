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
            .authorId(faker.internet().uuid())
            .id(faker.internet().uuid())
            .reactions(new HashSet<Reaction>())
            .content(faker.lorem().word())
            .createdAt(LocalDateTime.now())
            .comments(new HashSet<Comment>())
    }

    static def createComment() {
        return Comment.builder()
            .id(faker.internet().uuid())
            .authorId(faker.internet().uuid())
            .content(faker.lorem().word())
            .reactions(new HashSet<Reaction>())
            .createdAt(LocalDateTime.now())
    }

    static def createReaction() {
        def reactionsTypes = Reaction.Type.values()
        def randomReactionIndex = new Random()
                .nextInt(reactionsTypes.size())

        return Reaction.builder()
            .authorId(faker.internet().uuid())
            .type(reactionsTypes[randomReactionIndex])
    }

    static def createAuthor() {
        return Author.builder()
            .id(faker.internet().uuid())
            .email(faker.internet().emailAddress())
            .profileImage(faker.internet().image())
            .username(faker.name().username())
    }
}
