package org.gs;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "race")
public class RaceEntity extends PanacheEntity {

   public String race;
   public String description;






}
