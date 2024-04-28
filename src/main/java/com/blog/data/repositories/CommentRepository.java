package com.blog.data.repositories;

import com.blog.data.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<PostComment, UUID> {
}
