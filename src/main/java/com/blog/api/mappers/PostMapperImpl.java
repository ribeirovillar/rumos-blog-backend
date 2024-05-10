package com.blog.api.mappers;

import com.blog.api.dtos.AuthorDTO;
import com.blog.api.dtos.CommentDTO;
import com.blog.api.dtos.PostDTO;
import com.blog.data.models.Comment;
import com.blog.data.models.Post;
import com.blog.data.models.User;
import com.blog.domain.enums.CategoryEnum;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-05-11T00:12:03+0100",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Post toPost(PostDTO postDTO) {
        if ( postDTO == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( postDTO.title() );
        post.setContent( postDTO.content() );
        post.setCategories( categoryEnumArrayToCategoryEnumSet( postDTO.categories() ) );
        post.setAuthor( authorDTOToUser( postDTO.author() ) );
        post.setComments( commentDTOSetToCommentSet( postDTO.comments() ) );

        post.setCreated( java.time.LocalDateTime.now() );

        return post;
    }

    @Override
    public PostDTO toPostDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        AuthorDTO author = null;
        UUID id = null;
        String title = null;
        String content = null;
        LocalDateTime created = null;
        CategoryEnum[] categories = null;
        Set<CommentDTO> comments = null;

        author = userToAuthorDTO( post.getAuthor() );
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        created = post.getCreated();
        categories = categoryEnumSetToCategoryEnumArray( post.getCategories() );
        comments = commentSetToCommentDTOSet( post.getComments() );

        PostDTO postDTO = new PostDTO( id, title, content, created, categories, author, comments );

        return postDTO;
    }

    protected Set<CategoryEnum> categoryEnumArrayToCategoryEnumSet(CategoryEnum[] categoryEnumArray) {
        if ( categoryEnumArray == null ) {
            return null;
        }

        Set<CategoryEnum> set = new LinkedHashSet<CategoryEnum>( Math.max( (int) ( categoryEnumArray.length / .75f ) + 1, 16 ) );
        for ( CategoryEnum categoryEnum : categoryEnumArray ) {
            set.add( categoryEnum );
        }

        return set;
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

    protected Set<Comment> commentDTOSetToCommentSet(Set<CommentDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Comment> set1 = new LinkedHashSet<Comment>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CommentDTO commentDTO : set ) {
            set1.add( commentMapper.toComment( commentDTO ) );
        }

        return set1;
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

    protected CategoryEnum[] categoryEnumSetToCategoryEnumArray(Set<CategoryEnum> set) {
        if ( set == null ) {
            return null;
        }

        CategoryEnum[] categoryEnumTmp = new CategoryEnum[set.size()];
        int i = 0;
        for ( CategoryEnum categoryEnum : set ) {
            categoryEnumTmp[i] = categoryEnum;
            i++;
        }

        return categoryEnumTmp;
    }

    protected Set<CommentDTO> commentSetToCommentDTOSet(Set<Comment> set) {
        if ( set == null ) {
            return null;
        }

        Set<CommentDTO> set1 = new LinkedHashSet<CommentDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Comment comment : set ) {
            set1.add( commentMapper.toCommentDTO( comment ) );
        }

        return set1;
    }
}
