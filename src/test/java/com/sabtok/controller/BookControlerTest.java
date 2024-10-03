package com.sabtok.controller;

import com.sabtok.services.BookService;
import com.sabtok.services.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControlerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    BookControler bookControler;

    protected MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookControler).build();
    }

    @Test
    public void bookNumberTest() throws Exception {
        when(bookService.bookNumber()).thenReturn(1L);
        mockMvc.perform(MockMvcRequestBuilders.get("/book/booksCount")).andExpect(status().isOk());
    }

    @Test
    public void bookNumberTestException() throws Exception {
        when(bookService.bookNumber()).thenThrow(new RuntimeException());
        mockMvc.perform(MockMvcRequestBuilders.get("/book/booksCount")).andExpect(status().is5xxServerError());
    }
}
