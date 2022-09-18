package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //임베디드 내장타입 사용
    private Address address;

    @OneToMany(mappedBy = "member") //relation target
    private List<Order> orders = new ArrayList<>();
}
