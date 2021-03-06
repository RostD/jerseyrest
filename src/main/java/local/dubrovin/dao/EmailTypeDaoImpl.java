package local.dubrovin.dao;

import local.dubrovin.models.EmailType;
import local.dubrovin.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmailTypeDaoImpl extends AbstractDao implements local.dubrovin.dao.EmailTypeDao {

    @Override
    public EmailType findById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            EmailType type = session.get(EmailType.class, id);
            if (type == null) {
                throw new NotFoundException("Email-type not found");
            }
            return type;
        }
    }

    @Override
    public void save(EmailType emailType) {
        this.validateModel(emailType);
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(emailType);
            transaction.commit();
        }
    }

    @Override
    public void update(Integer typeId, EmailType emailType) {
        EmailType oldType = this.findById(typeId);
        this.validateModel(emailType);
        emailType.setId(oldType.getId());
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(emailType);
            transaction.commit();
        }
    }

    @Override
    public void delete(Integer id) {
        EmailType emailType = this.findById(id);
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(emailType);
            transaction.commit();
        }
    }

    @Override
    public List<EmailType> findAll() {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            return (List<EmailType>) session.createQuery("From EmailType").list();
        }
    }
}
