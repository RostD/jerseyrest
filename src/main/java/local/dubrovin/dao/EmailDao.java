package local.dubrovin.dao;

import local.dubrovin.models.Email;

import java.util.List;

public interface EmailDao {
    public Email findById(Integer bookId, Integer contactId, Integer emailId);

    public void save(Integer bookId, Integer contactId, Email email);

    public void update(Integer bookId, Integer contactId, Integer emailId, Email email);

    public void delete(Integer bookId, Integer contactId, Integer emailId);

    public List<Email> findAll(Integer bookId, Integer contactId);
}
