package org.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "DonHang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDonHang")
    private int idDonHang;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="NgayDatHang")
    Date NgayDatHang;

    @Column(name = "TongTienDonHang")
    Double TongTienDonHang;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="tenTK",referencedColumnName = "tenTk")
    private TaiKhoan tk;

    @OneToMany(mappedBy = "donHang")
    List<ChiTietDonHang> chiTietDonHangList;

}
