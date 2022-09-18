package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관계 맵핑 전략지정(한 테이블에 전부 저장)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //공동속성
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") //relation target
    private List<Category> categories = new ArrayList<>();


}
