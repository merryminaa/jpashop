package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     * **/
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * 상품 수정
     * **/
    //변경감지 기능 사용
    @Transactional
//    public void updateItem(Long itemId, Book bookParam) { //bookParam: 파라미터로 넘어온 준영속 상태의 엔티티
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); //같은 엔티티지만 영속상태인 것을 조회
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

//        findItem.setPrice(bookParam.getPrice());
//        findItem.setName(bookParam.getName());
//        findItem.setStockQuantity(bookParam.getStockQuantity());
        // flush, commit  시점에 dirty checking 통해 update query 
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}


