package com.blog.api.mappers;

import com.blog.api.dtos.AuthorDTO;
import com.blog.api.dtos.CommentDTO;
import com.blog.data.models.Comment;
import com.blog.data.models.Post;
import com.blog.data.models.User;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-05-11T00:12:03+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toComment(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setPost( commentDTOToPost( commentDTO ) );
        comment.setContent( commentDTO.content() );
        comment.setAuthor( authorDTOToUser( commentDTO.author() ) );

        comment.setCreated( java.time.LocalDateTime.now() );

        return comment;
    }

    @Override
    public CommentDTO toCommentDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        AuthorDTO author = null;
        UUID postId = null;
        UUID id = null;
        String content = null;
        LocalDateTime created = null;

        author = userToAuthorDTO( comment.getAuthor() );
        postId = commentPostId( comment );
        id = comment.getId();
        content = comment.getContent();
        created = comment.getCreated();

        CommentDTO commentDTO = new CommentDTO( id, content, author, postId, created );

        return commentDTO;
    }

    protected Post commentDTOToPost(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( commentDTO.postId() );

        return post;
    }

    protected User authorDTOToUser(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( authorDTO.id() );
        user.setEmail( authorDTO.email() );

        return user;
    }

    protected AuthorDTO userToAuthorDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;
        UUID id = null;

        email = user.getEmail();
        id = user.getId();

        String name = user.getPerson().getFirstName() + " " + user.getPerson().getLastName();

        AuthorDTO authorDTO = new AuthorDTO( id, name, email );

        return authorDTO;
    }

    private UUID commentPostId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Post post = comment.getPost();
        if ( post == null ) {
            return null;
        }
        UUID id = post.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
