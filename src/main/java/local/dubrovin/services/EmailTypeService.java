package local.dubrovin.services;

import local.dubrovin.dao.EmailTypeDao;
import local.dubrovin.dao.EmailTypeDaoImpl;
import local.dubrovin.models.EmailType;

import java.util.List;

public class EmailTypeService {

    private EmailTypeDao emailTypeDao = new EmailTypeDaoImpl();

    public EmailType find(Integer id) {
        return this.emailTypeDao.findById(id);
    }

    public void save(EmailType type) {
        this.emailTypeDao.save(type);
    }

    public List<EmailType> findAll() {
        return this.emailTypeDao.findAll();
    }

    public void update(Integer typeId, EmailType type) {
        this.emailTypeDao.update(typeId, type);
    }

    public void delete(Integer id) {
        this.emailTypeDao.delete(id);
    }
}
