package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //relation target
    private Order order;

    @Embedded
    private Address address; //임베디드 값타입

    @Enumerated(EnumType.STRING) //변경가능성을 고려하여 string으로 지정
    private DeliveryStatus status; //enum: READY, COMP
}
