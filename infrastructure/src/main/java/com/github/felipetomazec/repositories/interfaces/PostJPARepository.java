package com.github.felipetomazec.repositories.interfaces;

import com.github.felipetomazec.repositories.entities.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PostJPARepository extends JpaRepository<PostJpaEntity, String> {
    @Query("SELECT p.id as postId, COUNT(c) as commentCount FROM PostJpaEntity p LEFT JOIN CommentJpaEntity c ON p.id = c.post.id GROUP BY p.id")
    List<Map<String, Object>> countCommentsByPostIds(@Param("postIds") List<String> postIds);
}
