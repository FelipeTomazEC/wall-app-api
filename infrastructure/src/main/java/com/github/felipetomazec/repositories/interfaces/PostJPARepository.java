package com.github.felipetomazec.repositories.interfaces;

import com.github.felipetomazec.repositories.entities.PostJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJPARepository extends JpaRepository<PostJpaEntity, String> {

}
