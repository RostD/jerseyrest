package local.dubrovin.services;

import local.dubrovin.dao.EmailDao;
import local.dubrovin.dao.EmailDaoImpl;
import local.dubrovin.models.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EmailService {
    private EmailDao emailDao = new EmailDaoImpl();

    @Getter
    @Setter
    private Integer bookId;

    @Getter
    @Setter
    private Integer contactId;

    public EmailService(Integer bookId, Integer contactId) {
        this.bookId = bookId;
        this.contactId = contactId;
    }

    public List<Email> findAll() {
        return emailDao.findAll(this.bookId, this.contactId);
    }

    public void save(Email email) {
        emailDao.save(this.bookId, this.contactId, email);
    }

    public void update(Integer emailId, Email email) {
        this.emailDao.update(this.bookId, this.contactId, emailId, email);
    }

    public void delete(Integer emailId) {
        this.emailDao.delete(this.bookId, this.contactId, emailId);
    }

    public Email findById(Integer emailId) {
        return this.emailDao.findById(this.bookId, this.contactId, emailId);
    }
}
