package org.denizkpln.provinceservice.repository;

import org.denizkpln.provinceservice.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province,Long> {
    Province findByName(String name);
}
