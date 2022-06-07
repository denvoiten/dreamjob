package ru.job4j.control;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.model.City;
import ru.job4j.model.Post;
import ru.job4j.service.CityService;
import ru.job4j.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class PostControllerTest {
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        HttpSession session = mock(HttpSession.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(
                1, "New post",
                "description", true,
                new City(1, "Msk"),
                LocalDateTime.now().toString()
        );
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(
                1, "New post",
                "description", true,
                new City(1, "Msk"),
                LocalDateTime.now().toString()
        );
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.updatePost(post);
        verify(postService).update(post);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenAddPosts() {
        Post post = new Post();
        List<City> cityList = Arrays.asList(
                new City(1, "Spb"),
                new City(2, "Msk")
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(cityService.getAllCities()).thenReturn(cityList);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.addPost(model, session);
        verify(model).addAttribute("post", post);
        verify(model).addAttribute("cities", cityList);
        assertThat(page, is("addPost"));
    }
}