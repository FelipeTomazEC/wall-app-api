package com.github.felipetomazec.entities


import spock.lang.Specification

class PostTest extends Specification {

    def "Should add only the last reaction of a given author"() {
        given:
        def authorOne = UUID.randomUUID()
        def authorTwo = UUID.randomUUID()

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
