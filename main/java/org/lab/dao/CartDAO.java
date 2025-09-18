package org.lab.dao;

import org.lab.model.SanPhamBia;

import java.util.Map;

public interface CartDAO {

    void addSanPham(Integer id);

    void removeSanPham (Integer id);

    void updateSanPham (Integer id,int qty);

    void clear();

    Map<Integer, SanPhamBia> getSanPham();

    int getSoluong();

    double getTongTien();
}
