package local.dubrovin.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emails")
@EqualsAndHashCode
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "email")
    @Getter
    @Setter
    @NotEmpty(message = "email is cannot be empty")
    @Length(max = 50, message = "The field must be less than 50 characters")
    private String email;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    @Getter
    @Setter
    @JsonIgnore
    private Contact contact;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "type_id")
    @Getter
    @Setter
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EmailType type;

    @Setter
    @Getter
    @NotNull(message = "typeId is cannot be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private Integer typeId;

}
