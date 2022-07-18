package com.dh.hospedagem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Usuario implements Serializable {
    private static final long serialVersionUID= 1L;

    @EqualsAndHashCode.Include
    @Id
//    @GeneratedValue(generator = "increment") // PostgreSQL
//    @GenericGenerator(name = "increment", strategy = "increment") // PostegreSQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    @Column(unique = true)
    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

//    @JsonIgnore
    @ManyToOne
    private Funcao funcao;

//    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private Set<Reserva> reserva = new HashSet<>();

}
