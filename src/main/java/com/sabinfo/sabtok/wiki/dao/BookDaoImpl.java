package com.sabinfo.sabtok.wiki.dao;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.management.Query;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sabinfo.sabtok.wiki.controller.HomeController1;
import com.sabinfo.sabtok.wiki.model.Attachement;
import com.sabinfo.sabtok.wiki.model.Book;
import com.sabinfo.sabtok.wiki.model.Page;
import com.sabinfo.sabtok.wiki.model.PageComparator;

@Repository
public class BookDaoImpl {

	@Autowired
	SessionFactory sessionFactory;

	private static Logger logger = Logger.getLogger(BookDaoImpl.class);

	public void saveBook(Book book) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.getTransaction();
			tx.begin();
			session.merge(book);
			tx.commit();
			logger.debug("Saved book " + book.getBookName());
		} catch (Exception e) {
			logger.error("Error while saving book " + book.getBookName(), e);
		} finally {
			session.close();
		}
	}

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.getTransaction();
			tx.begin();
			org.hibernate.Query query = session.createQuery("from Book");
			books = query.list();
			Collections.sort(books);
			tx.commit();
			logger.debug(books);
		} catch (Exception e) {
			logger.error("Error while fetching books record from data base ", e);
		} finally {
			session.close();
		}
		return books;
	}

	public Book getBook(String bookId) {
		Book book;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		book = (Book) session.get(Book.class, bookId);
		tx.commit();
		session.close();
		return book;
	}

	public void savePage(Page page) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.merge(page);
		tx.commit();
		session.close();
	}

	public List<Page> getPages() {
		List<Page> pages;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		org.hibernate.Query query = session.createQuery("from Page");
		pages = query.list();
		Collections.sort(pages);
		tx.commit();
		session.close();
		return pages;
	}

	public List<Page> getPages(String bookID) {
		List<Page> pages;
		org.hibernate.Query query;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		query = session.createQuery("from Page where BOOKID =" + "'" + bookID + "'");
		pages = query.list();
		Collections.sort(pages,new PageComparator());
		tx.commit();
		session.close();
		return pages;
	}

	public Page getPageDetails(String pageId) {
		Page page;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		page = (Page) session.get(Page.class, pageId);
		tx.commit();
		session.close();
		return page;
	}

	public void updatePageDetails(Page page) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.update(page);
		tx.commit();
		session.close();
	}

	public void addDocument(Attachement doc) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.merge(doc);
		tx.commit();
		session.close();
	}

	public Blob getBlob(byte[] is) {
		Blob blob;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		blob = session.getLobHelper().createBlob(is);
		tx.commit();
		session.close();
		return blob;
	}

	public Attachement getAttachment(String attachementNo) {
		Attachement doc;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		doc = (Attachement) session.get(Attachement.class, attachementNo);
		tx.commit();
		session.close();
		return doc;
	}

	public List<Attachement> getAttachedDocuments(String pageId) {
		List<Attachement> docs;
		org.hibernate.Query query;
		Session session = sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		query = session.createQuery("from Attachement where PAGE_ID =" + "'" + pageId + "'");
		docs = query.list();
		Collections.sort(docs);
		tx.commit();
		session.close();
		return docs;
	}
	
	public Object getTotalNumberOfBooks(){
		Session session = sessionFactory.openSession();
		return session.createCriteria(Book.class).setProjection(Projections.max("bookNo")).list().get(0);
	}
	
	public Object getTotalNumberOfPages(String bookId){
		Session session = sessionFactory.openSession();
		Object tottalPages = session.createCriteria(Page.class).setProjection(Projections.max("pageNo")).add(Restrictions.eq("bookId", bookId)).list().get(0);
		if (tottalPages == null)
			tottalPages = 0;
		return tottalPages;
	}
	public Object getTotalNumberOfAttachedDocs(String pageId){
		Session session = sessionFactory.openSession();
		Object totalNo = session.createCriteria(Attachement.class).setProjection(Projections.max("attachementNo")).add(Restrictions.eq("pageId",pageId)).list().get(0);
		if (totalNo == null)
			totalNo =0;
		return totalNo;
	}
	public static void main(String[] args) {
		BookDaoImpl a = new BookDaoImpl();
		System.out.println(a.getBooks());
	}
}
