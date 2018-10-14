package local.dubrovin.services;

import local.dubrovin.dao.ContactDao;
import local.dubrovin.dao.ContactDaoImpl;
import local.dubrovin.models.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ContactService {
    private ContactDao contactDao = new ContactDaoImpl();

    @Getter
    @Setter
    private Integer bookId;

    public ContactService(Integer bookId) {
        this.bookId = bookId;
    }

    public void save(Contact contact) {
        this.contactDao.save(this.bookId, contact);
    }

    public void update(Integer contactId, Contact contact) {
        this.contactDao.update(this.bookId, contactId, contact);
    }

    public void delete(Integer contactId) {
        this.contactDao.delete(this.bookId, contactId);
    }

    public List<Contact> findAll() {
        return this.contactDao.findAll(this.bookId);
    }

    public Contact findById(Integer contactId) {
        return this.contactDao.findById(this.bookId, contactId);
    }
}
