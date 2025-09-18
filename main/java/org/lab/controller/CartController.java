package org.lab.controller;

import org.lab.dao.CartDAO;
import org.lab.dao.TaiKhoanDAO;
import org.lab.model.CartService;
import org.lab.model.TaiKhoan;
import org.lab.utils.ParamService;
import org.lab.utils.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class CartController {

    @Autowired
    CartService dao;

    @Autowired
    SessionService sessionService;

    @Autowired
    ParamService paramService;

    @Autowired
    TaiKhoanDAO tkDAO;

    @RequestMapping("/cart")
    public String cart(Model model){
        model.addAttribute("cart",dao.getSanPham());
        model.addAttribute("tongtien",dao.getTongTien());
        return "cart";
    }

    @RequestMapping("/cart/add/{id}")
    public String cart_add(Model model, @PathVariable("id") int id, RedirectAttributes re){
        dao.addSanPham(id);
        sessionService.set("cartSoLuong",dao.getSoluong());
        re.addAttribute("message","Đã thêm vào giỏ hàng");
        return "redirect:/product";
    }

    @PostMapping("/cart/update/{id}")
    public String cart_update(Model model, @PathVariable("id") int id){
        dao.updateSanPham(id,paramService.getInt("quantity",0));
        model.addAttribute("Cập nhật vào giỏ hàng");
        return "redirect:/cart";
    }

    @RequestMapping("/cart/remove/{id}")
    public String cart_delete(@PathVariable("id") int id ,Model model){
        dao.removeSanPham(id);
        model.addAttribute("message","Cập nhật vào giỏ hàng");
        return "redirect:/cart";
    }

    @RequestMapping("/cart/clear")
    public String cart_clear(Model model){
        dao.clear();
        model.addAttribute("message","Dọn giỏ hàng");
        return "redirect:/cart";
    }

    @RequestMapping("/cart/{account}/check-out")
    public String cart_bill(@PathVariable String account,RedirectAttributes ra){
        TaiKhoan tk = tkDAO.findById(account).get();
        if(tk!=null){
            return "checkbill";
        }else{
            ra.addFlashAttribute("error","Vui lòng đăng nhập để tiếp tục");
            return "redirect:/login";
        }
    }

    @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
