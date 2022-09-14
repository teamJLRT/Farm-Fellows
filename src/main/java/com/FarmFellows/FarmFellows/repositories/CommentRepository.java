package com.FarmFellows.FarmFellows.repositories;

import com.FarmFellows.FarmFellows.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
