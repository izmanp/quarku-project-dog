package org.gs;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class DogsRepository implements PanacheRepository<DogsEntity> {

    public DogsEntity findByName(String name) {
        return find("name", name).firstResult();
    }


    public void deleteStefs() {
        delete("name", "Stef");
    }
}
