package swd.SWDProject.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "PRODUCT_ORDER")
public class ProductOrder {

    @Id
    private int id;
    @Column(name = "ORDER_ID")
    private int orderId;
    @Column(name = "PRODUCT_ID")
    private int productId;
    private int quantity;
}
