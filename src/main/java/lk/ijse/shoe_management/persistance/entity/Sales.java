package lk.ijse.shoe_management.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "added_points")
    private Double addedPoints;

    @Column(name = "cashier_name")
    private String cashierName;
}
