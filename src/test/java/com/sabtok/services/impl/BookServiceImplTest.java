package com.sabtok.services.impl;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sabtok.dao.BookDao;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {

	@InjectMocks
	BookDao bookDao;

	@Mock
	BookServiceImpl bookServiceImpl;

	@Test
	public void bookNumberTest() {

		Mockito.when(bookDao.count()).thenReturn(new Long(0));
		assertEquals(new Long(0), bookServiceImpl.bookNumber());
	}

}
