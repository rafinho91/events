package pl.sda.events.service;

import pl.sda.events.model.CommentEntity;

import java.util.List;

public interface CommentService {

    void addComment(CommentEntity commentEntity);
    List<CommentEntity> findCommentByUserEntityId(Long userId);
    List<CommentEntity> findCommentByEventEntityId(Long eventId);
}
