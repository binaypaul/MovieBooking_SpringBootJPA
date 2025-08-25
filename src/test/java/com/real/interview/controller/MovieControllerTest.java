package com.real.interview.controller;

import com.real.interview.entity.MovieEntity;
import com.real.interview.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    private MovieEntity movieEntity;

    @BeforeEach
    void setUp() {
        movieEntity = new MovieEntity();
        movieEntity.setId(1L);
        movieEntity.setName("Test Movie");
        movieEntity.setRating(8);
    }

    @Test
    void testGetAll() throws Exception {
        Page<MovieEntity> page = new PageImpl<>(List.of(movieEntity));
        when(movieService.getAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/movie/getall")
                        .param("page", "0")         //both are identical
                        .queryParam("size", "5")    //both are identical
                        .param("sortBy", "id")
                        .param("asc", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Test Movie"));
    }

    @Test
    void testCreate() throws Exception {
        when(movieService.addMovie(any(MovieEntity.class))).thenReturn(movieEntity);

        mockMvc.perform(post("/movie/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Movie\",\"rating\":8}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Movie"));
    }

    @Test
    void testGet() throws Exception {
        when(movieService.getMovie(1L)).thenReturn(movieEntity);

        mockMvc.perform(get("/movie/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Movie"));
    }

    @Test
    void testUpdate() throws Exception {
        when(movieService.update(any(MovieEntity.class))).thenReturn(movieEntity);
        mockMvc.perform(put("/movie/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"3Idiots\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("3Idiots"));
    }

    @Test
    void testDelete() throws Exception {
        when(movieService.delete(1L)).thenReturn("DELETED");
        mockMvc.perform(delete("/movie/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("DELETED"));
    }
}