package pl.sda.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.events.model.CommentEntity;
import pl.sda.events.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(CommentEntity commentEntity) {
        commentRepository.save(commentEntity);
    }

    @Override
    public List<CommentEntity> findCommentByUserEntityId(Long userId) {
        return commentRepository.findByUserEntityId(userId);
    }

    @Override
    public List<CommentEntity> findCommentByEventEntityId(Long eventId) {
        return commentRepository.findByEventEntityId(eventId);
    }
}
