package hello.itemservice.enumPractise;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemType;

public class Workspace {

    public static void main(String[] args) {
//        Delivery deliveryType = Delivery.FAST;
//
//        System.out.println("deliveryType = " + deliveryType);

        Item item = new Item();
        item.setItemType(ItemType.BOOK);
        System.out.println(item.getItemType());

        ItemType[] values = ItemType.values();
        for (ItemType itemType : values) {
            System.out.println("itemType = " + itemType);
        }

    }
}

