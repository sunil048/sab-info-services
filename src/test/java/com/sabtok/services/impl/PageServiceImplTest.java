package com.sabtok.services.impl;

import com.sabtok.dao.PageDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PageServiceImplTest {

    @Mock
    private PageDao pageRepo;

    @InjectMocks
    private PageServiceImpl pageServiceImpl;

    @Test
    public void getPageCountTest(){
        when(pageRepo.count()).thenReturn(1L);
        Long count = pageServiceImpl.getPageCount();
        assertEquals(1L,count);
    }
}
