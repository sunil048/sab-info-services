package com.sabtok.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabtok.entity.Page;
import com.sabtok.services.PageService;
import com.sabtok.services.impl.PageActivityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.sabtok.ApiCons.PAGE;
import static com.sabtok.ApiCons.SAVE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PageControllerTest {

    @Mock
    private PageService pageService;

    @InjectMocks
    private PageController pageController;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(pageController).build();
    }

    @Test
    public void creatPageTest() throws Exception {
        Page request = new Page();
        when(pageService.creatPage(any())).thenReturn(1);
        mockMvc.perform(MockMvcRequestBuilders.post(PAGE+SAVE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(request)))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
