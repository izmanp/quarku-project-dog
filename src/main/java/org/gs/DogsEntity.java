package org.gs;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.EnumType.STRING;

enum Color {white, black, brown, red, gold, bicolor, grey, tricolor, merle};
@Entity
@Table(name = "dogsentity")

public class DogsEntity extends PanacheEntity {

    public String name;
    public int age;
    @Enumerated(STRING)
    public Color color;





}
