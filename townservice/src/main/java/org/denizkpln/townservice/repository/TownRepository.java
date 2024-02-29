package org.denizkpln.townservice.repository;

import org.denizkpln.townservice.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TownRepository extends JpaRepository<Town,Long>{
    @Query(value="SELECT exists(select *from town where name=?)",nativeQuery = true)
    Boolean existsByName(String name);

    List<Town> findByName(String name);
}
