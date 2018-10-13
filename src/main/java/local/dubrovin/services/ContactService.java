package local.dubrovin.services;

import local.dubrovin.dao.ContactDao;
import local.dubrovin.dao.ContactDaoImpl;
import local.dubrovin.models.Contact;

import java.util.List;

public class ContactService {
    private ContactDao contactDao = new ContactDaoImpl();

    public void save(Contact contact) {
        this.contactDao.save(contact);
    }

    public void update(Contact contact) {
        this.contactDao.update(contact);
    }

    public void delete(Contact contact) {
        this.contactDao.delete(contact);
    }

    public List<Contact> findAll(Integer bookID) {
        return this.contactDao.findAll(bookID);
    }

    public Contact findById(Integer bookId, Integer contactId) {
        return this.contactDao.findById(bookId, contactId);
    }
}
