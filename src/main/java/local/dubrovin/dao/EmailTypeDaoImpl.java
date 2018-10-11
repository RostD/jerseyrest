package local.dubrovin.dao;

import local.dubrovin.models.EmailType;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmailTypeDaoImpl implements local.dubrovin.dao.EmailTypeDao {

    @Override
    public EmailType findById(Integer id) {
        return HibernateSessionFactoryUtil.getFactory().openSession().get(EmailType.class,id);
    }

    @Override
    public void save(EmailType emailType) {
        Session session = HibernateSessionFactoryUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(emailType);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(EmailType emailType) {
        Session session = HibernateSessionFactoryUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(emailType);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(EmailType emailType) {
        Session session = HibernateSessionFactoryUtil.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(emailType);
        transaction.commit();
        session.close();
    }

    @Override
    public List<EmailType> findAll() {
        Session session = HibernateSessionFactoryUtil.getFactory().openSession();
        List<EmailType> types = (List<EmailType>) session.createQuery("From EmailType").list();
        return types;
    }
}
