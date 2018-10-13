package local.dubrovin.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @Getter
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @Getter
    @Setter
    private EmailType type;
}
