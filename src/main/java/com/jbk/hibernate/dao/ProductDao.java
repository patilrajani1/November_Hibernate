package com.jbk.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jbk.hibernate.config.HibernateConfig;
import com.jbk.hibernate.entity.Product;

public class ProductDao {
	
	private SessionFactory sessionFactory=HibernateConfig.getSessionFactory();
	
	public boolean saveProduct(Product product) {
		
		Session session=sessionFactory.openSession();
		boolean isAdded=false;
		
		try {
			
			Product dbProduct = getProductById(product.getProductId());
    
			if(dbProduct==null) {
		
				Transaction transaction = session.beginTransaction();
				session.save(product);  //insert into //save, update, delete
				transaction.commit();
				isAdded=true;
			}	
			
		} catch (Exception e) {
              e.printStackTrace();
		
		}finally {
			session.close();
		}
		return isAdded;
		
	}
	
	public Product getProductById(long productId) {
		Session session=sessionFactory.openSession();
		Product product=null;
		try {
			 product = session.get(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			session.close();
		}
		
		
		return product;
		
	}

	public boolean deleteProductById(long productId) {
		
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isDeleted=false;
		
		try {
			Product dbProduct = getProductById(productId);
			
		if (dbProduct != null) {
			
			session.delete(dbProduct);
			transaction.commit();
			isDeleted=true;
			
		} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return isDeleted;
	}

	public boolean updateProduct(Product product) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUpdated = false;
		
		try {
			Product dbProduct = getProductById(product.getProductId());
			
			if(dbProduct != null) {
				session.update(product);
				transaction.commit();
				isUpdated = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}

		return isUpdated;
	}

	public List<Product> getAllProduct() {
		Session session=sessionFactory.openSession();
		List<Product> list = null;
		
		try {
			Criteria criteria = session.createCriteria(Product.class);
			
			
			//criteria.add(Restrictions.gt("productPrice", 123d)); //greater than
			//criteria.setFirstResult(1);
			//criteria.setMaxResults(3); //show first 3 record  
			criteria.addOrder(Order.desc("productName")); // List display: assending/decending order
			list = criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return list;
	}
}
