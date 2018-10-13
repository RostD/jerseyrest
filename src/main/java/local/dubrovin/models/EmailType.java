package local.dubrovin.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "email_types")
@XmlRootElement
public class EmailType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    @Getter
    @Setter
    private Integer id;

    @Column(name = "name")
    @XmlElement
    @NotEmpty(message = "name can't be empty")
    @Length(max = 20, message = "The field must be less than 20 characters")
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "type")
    @Getter
    private List<Email> emails;

    public EmailType() {
    }

    public EmailType(String name) {
        this.name = name;
    }
}
