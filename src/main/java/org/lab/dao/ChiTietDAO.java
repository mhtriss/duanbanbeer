package org.lab.dao;

import org.lab.model.ChiTietDonHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietDAO extends JpaRepository<ChiTietDonHang,Integer> {
}
