package dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.*;
import org.hibernate.query.Query;
import util.HibernateUtil;
import util.LoggerUtil;

import java.util.logging.Logger;

public class BaseDAO {
    private static final Logger logger = LoggerUtil.getLogger();

    public static void insertData(Object entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity); // Save the entity
            tx.commit();  // Commit the transaction
            logger.info("Data inserted successfully: " + entity.toString());
        } catch (HibernateException e) {
            if (tx != null) {
                logger.severe("Error inserting data: " + e.getMessage() + " Rolling back transaction.");
                tx.rollback();  // Rollback if any exception occurs
            }
            logger.severe("Error inserting data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateData(Object entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(entity); // Update the entity
            tx.commit();  // Commit the transaction
            logger.info("Data updated successfully: " + entity.toString());
        } catch (HibernateException e) {
            if (tx != null) {
                logger.severe("Error updating data: " + e.getMessage() + " Rolling back transaction.");
                tx.rollback();  // Rollback if any exception occurs
            }
            logger.severe("Error updating data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deleteData(Object entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(entity); // Delete the entity
            tx.commit();  // Commit the transaction
            logger.info("Data deleted successfully: " + entity.toString());
        } catch (HibernateException e) {
            if (tx != null) {
                logger.severe("Error deleting data: " + e.getMessage() + " Rolling back transaction.");
                tx.rollback();  // Rollback if any exception occurs
            }
            logger.severe("Error deleting data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void getAllData(Class<?> entityClass) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<?> criteria = builder.createQuery(entityClass);
            criteria.from(entityClass);
            Query<?> query = session.createQuery(criteria);
            logger.info("Fetching all data for: " + entityClass.getSimpleName());
            query.getResultList().forEach(System.out::println);
        } catch (HibernateException e) {
            logger.severe("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
