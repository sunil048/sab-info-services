package com.sabinfo.sabtok.wiki.dao.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sabinfo.sabtok.wiki.dao.ActivityDAO;
import com.sabinfo.sabtok.wiki.db.IDGenerater;
import com.sabinfo.sabtok.wiki.db.IDGenerater.ACTIVITY;
import com.sabinfo.sabtok.wiki.model.Activity;

public class ActivityDAOImpl implements ActivityDAO {

	public void saveActivity(Activity activity) {
		
		try {
			SessionFactory fact = new Configuration().configure().buildSessionFactory();
			Session session = fact.openSession();
			session.beginTransaction();
			session.save(activity);
			session.getTransaction().commit();
			session.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Activity ac = new Activity();
		ac.setId("5");
		ac.setItemId("hyb123");
		ac.setLogDate(new Date());
		ac.setAction(String.valueOf(IDGenerater.ACTIVITY.ADDED));
		ActivityDAOImpl k = new ActivityDAOImpl();
		k.saveActivity(ac);
	}

}
