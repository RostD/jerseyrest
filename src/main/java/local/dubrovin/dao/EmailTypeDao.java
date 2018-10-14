package local.dubrovin.dao;

import local.dubrovin.models.EmailType;

import java.util.List;

public interface EmailTypeDao {
    public EmailType findById(Integer id);

    public void save(EmailType emailType);

    public void update(Integer typeId, EmailType emailType);

    public void delete(Integer id);

    public List<EmailType> findAll();
}
