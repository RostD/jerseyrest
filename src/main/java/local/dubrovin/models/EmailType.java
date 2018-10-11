package local.dubrovin.models;



import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "email_types")
@Data
@XmlRootElement
public class EmailType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Integer id;

    @Column(name = "name")
    @XmlElement
    @NotEmpty(message = "name can't be empty")
    private String name;

    public EmailType() {
    }

    public EmailType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "models.EmailType{id=" + this.id + ", name=" + this.name + "}";
    }
}
