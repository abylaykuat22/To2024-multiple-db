package bitlab.to2024g1multipledb.service;

import bitlab.to2024g1multipledb.amirdb.entity.Item;
import bitlab.to2024g1multipledb.amirdb.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  public List<Item> getItems() {
    return itemRepository.findAll();
  }
}
