package org.lab.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.lab.dao.CartDAO;
import org.lab.dao.SanPhamBiaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@SessionScope
public class CartService implements CartDAO {

    @Autowired
    SanPhamBiaDAO dao;

    HashMap<Integer,SanPhamBia> order = new HashMap<>();
    @Override
    public void addSanPham(Integer id) {
        SanPhamBia sp = dao.findById(id).get().cartQty(1);
        if(!order.containsKey(id)){
            order.put(sp.getIdSanPham(),sp);
        }else if(sp!=null){
            int oldQty = order.get(id).getSoLuongSanPham();
            order.get(id).setSoLuongSanPham(oldQty + 1);
        }

    }

    @Override
    public void removeSanPham(Integer id) {
        SanPhamBia sp = order.get(id);
        dao.findById(id).get().cartQty(0-sp.getSoLuongSanPham());
        order.remove(id);
    }

    @Override
    public void updateSanPham(Integer id, int qty) {
        SanPhamBia sp = order.get(id);
        SanPhamBia spcheck = dao.findById(id).get().cartQty(qty-sp.getSoLuongSanPham());
        if(qty > 0){
            if(spcheck!=null){
                sp.setSoLuongSanPham(qty);
            }
        }else {
            order.remove(id);
        }
    }

    @Override
    public void clear() {
        for(Integer id : order.keySet()){
            SanPhamBia sp = order.get(id);
            dao.findById(id).get().cartQty(0-sp.getSoLuongSanPham());
        }
        order.clear();
    }

    @Override
    public Map<Integer, SanPhamBia> getSanPham() {
        return order;
    }

    @Override
    public int getSoluong() {
        int dem = 0;
        for(SanPhamBia sp : order.values()){
            dem += sp.getSoLuongSanPham();
        }
        return dem;
    }

    @Override
    public double getTongTien() {
        double TongTien = 0;
        for(SanPhamBia sp : order.values()){
            TongTien += sp.getGiaSanPham() * sp.getSoLuongSanPham();
        }
        return TongTien;
    }
}
