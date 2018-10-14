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

    public void update(Contact contact) {
        this.contactDao.update(contact);
    }

    public void delete(Contact contact) {
        this.contactDao.delete(contact);
    }

    public List<Contact> findAll() {
        return this.contactDao.findAll(this.bookId);
    }

    public Contact findById(Integer contactId) {
        return this.contactDao.findById(this.bookId, contactId);
    }
}
