package com.github.felipetomazec.entities

import net.datafaker.Faker
import spock.lang.Specification

class PostTest extends Specification {
    def faker = new Faker()

    def "Should add only the last reaction of a given author"() {
        given:
        def authorOne = faker.internet().uuid()
        def authorTwo = faker.internet().uuid()

        def reactionAuthorOne = TestUtils.createReaction()
            .authorId(authorOne)
            .type(Reaction.Type.DISLIKE)
            .build()

        def reactionAuthorTwo = TestUtils.createReaction()
            .authorId(authorTwo)
            .build()


        def newReactionAuthorOne = TestUtils.createReaction()
                .authorId(authorOne)
                .type(Reaction.Type.LIKE)
                .build()

        def post = TestUtils.createPost().build()

        when:
        post.addReaction(reactionAuthorOne)
        post.addReaction(reactionAuthorTwo)

        and:
        post.addReaction(newReactionAuthorOne)

        then:
        def postReactions = post.getReactions()
        postReactions.contains(reactionAuthorTwo)
        postReactions.contains(newReactionAuthorOne)
        !postReactions.contains(reactionAuthorOne)
    }

    def "Should accept adding multiple comments"() {
        given:
        def commentAuthorId = faker.internet().uuid()
        def commentOne = TestUtils.createComment()
                .authorId(commentAuthorId)
                .build()
        def commentTwo = TestUtils.createComment()
                .authorId(commentAuthorId)
                .build()
        def post = TestUtils.createPost().build()

        when:
        post.addComment(commentOne)

        and:
        post.addComment(commentTwo)

        then:
        post.getComments().size() == 2
    }

    def "Should remove a comment"() {
        given:
        def post = TestUtils.createPost().build()
        def comment = TestUtils.createComment().build()
        post.addComment(comment)

        when:
        post.removeComment(comment)

        then:
        post.getComments().isEmpty()
    }

    def "Should remove a reaction"() {
        given:
        def post = TestUtils.createPost().build()
        def reaction = TestUtils.createReaction().build()
        post.addReaction(reaction)

        when:
        post.removeReaction(reaction)

        then:
        post.getReactions().isEmpty()
    }
}
