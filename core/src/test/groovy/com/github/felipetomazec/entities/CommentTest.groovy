package com.github.felipetomazec.entities

import net.datafaker.Faker
import spock.lang.Specification

class CommentTest extends Specification {
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

        def comment = TestUtils.createComment().build()

        when:
        comment.addReaction(reactionAuthorOne)
        comment.addReaction(reactionAuthorTwo)

        and:
        comment.addReaction(newReactionAuthorOne)

        then:
        def commentReactions = comment.getReactions()
        commentReactions.contains(reactionAuthorTwo)
        commentReactions.contains(newReactionAuthorOne)
        !commentReactions.contains(reactionAuthorOne)
    }

    def "Should remove a reaction"() {
        given:
        def comment = TestUtils.createComment().build()
        def reaction = TestUtils.createReaction().build()
        comment.addReaction(reaction)

        when:
        comment.removeReaction(reaction)

        then:
        comment.getReactions().isEmpty()
    }
}
