package com.github.felipetomazec.usecases.retrieveallposts;

import com.github.felipetomazec.entities.Post;
import com.github.felipetomazec.interfaces.UseCase;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.GetAllPostsRepository;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.GetAllAuthorsRepository;
import com.github.felipetomazec.usecases.retrieveallposts.dependencies.RetrieveAllPostsPresenter;
import com.github.felipetomazec.usecases.retrieveallposts.dtos.RetrieveAllPostsOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveAllPostsUseCase implements UseCase<Void, RetrieveAllPostsOutput> {

    private final GetAllPostsRepository postsRepository;
    private final RetrieveAllPostsPresenter presenter;
    private final GetAllAuthorsRepository authorsRepository;

    @Override
    public RetrieveAllPostsOutput execute(Void unused) {
        var posts = postsRepository.getAll();

        var authorsId = posts.stream()
                .map(Post::getAuthorId)
                .collect(Collectors.toSet());

        var authors = authorsRepository.getAuthorByIds(authorsId);

        return presenter.success(posts, authors);
    }
}
