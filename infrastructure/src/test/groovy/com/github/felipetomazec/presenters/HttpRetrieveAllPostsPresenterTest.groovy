package com.github.felipetomazec.presenters

import com.github.felipetomazec.entities.Reaction
import com.github.felipetomazec.utils.TestUtils
import spock.lang.Specification

class HttpRetrieveAllPostsPresenterTest extends Specification {
    def sut = new HttpRetrieveAllPostsPresenter()

    def "Should produce the partial post collection correctly"() {
        given:
        def chris = TestUtils.createAuthor().username("Chris").build()
        def greg = TestUtils.createAuthor().username("Greg").build()
        def julius = TestUtils.createAuthor().username("Julius").build()
        def rochelle = TestUtils.createAuthor().username("Rochelle").build()
        def tonya = TestUtils.createAuthor().username("Tonya").build()

        and: "Chris creates a post"
        def post = TestUtils.createPost()
                .authorId(chris.id)
                .build()

        and: "people start reacting to his posts"
        post.addReaction(Reaction.builder()
                .authorId(greg.id)
                .type(Reaction.Type.LIKE)
                .build()
        )

        post.addReaction(Reaction.builder()
                .authorId(rochelle.id)
                .type(Reaction.Type.LOVE)
                .build()
        )

        post.addReaction(Reaction.builder()
                .authorId(julius.id)
                .type(Reaction.Type.LOVE)
                .build()
        )

        post.addReaction(Reaction.builder()
                .authorId(tonya.id)
                .type(Reaction.Type.DISLIKE)
                .build()
        )

        and: "also giving some comments"
        def numberOfComments = 3
        def postWithCommentsCount = [(post): numberOfComments]

        when:
        def response = sut.success(postWithCommentsCount, [chris]).posts()

        then:
        response.size() == 1

        and:
        def postInfo = response[0]
        postInfo.author.id == chris.id.toString()
        postInfo.author.avatar == chris.getProfileImage()
        postInfo.author.username == chris.username
        postInfo.content == post.content
        postInfo.numberOfComments == 3
        postInfo.postedAt == post.createdAt
        postInfo.id == post.id.toString()

        and:
        def reactionsByType = response[0].numberOfReactionsByType
        reactionsByType[Reaction.Type.DISLIKE] == 1
        reactionsByType[Reaction.Type.LIKE] == 1
        reactionsByType[Reaction.Type.LOVE] == 2
    }
}
