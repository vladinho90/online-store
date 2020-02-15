package com.sda.grupa11.onlinestore.service.impl;


import com.sda.grupa11.onlinestore.model.Cart;
import com.sda.grupa11.onlinestore.model.CartItem;
import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.repository.CartItemRepository;
import com.sda.grupa11.onlinestore.repository.CartRepository;
import com.sda.grupa11.onlinestore.service.ICartService;
import com.sda.grupa11.onlinestore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    IOrderService orderService;

    @Autowired
    public CartRepository cartRepository;


    @Autowired
    public CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }

    @Override
    public void delete(Long cartItemId , User user){
       Optional<CartItem> cartItem= getCart(user).getCartItemSet()
               .stream().filter(item-> cartItemId.equals(item.getProduct().getId())).findFirst();
       cartItem.ifPresent(productInOrder -> {
           productInOrder.setCart(null);
           cartRepository.deleteById(productInOrder.getId());
       });
    }


    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }



    @Override
    public void addToCart(CartItem cartItem, User user){
        Cart finalCart = user.getCart();
        Set<CartItem> set = finalCart.getCartItemSet();
        Optional<CartItem> old = set.stream().filter(item -> item.getProduct().getId().equals(cartItem.getProduct().getId())).findFirst();
        CartItem item;
        if(old.isPresent()){
            item=old.get();
            item.setQuantity(cartItem.getQuantity()+item.getQuantity());
        }else{
            item= cartItem;
            item.setCart(finalCart);
            finalCart.getCartItemSet().add(item);
        }
        cartItemRepository.save(item);

        cartRepository.save(finalCart);
    }

    @Override
    public void deleteCart(Long cartItemId, User user) {
        Optional<CartItem> cartItemOptional = user.getCart().getCartItemSet().stream().filter(itemId -> cartItemId.equals(itemId.getId())).findFirst();
        cartItemOptional.ifPresent(cartItem ->{
            cartItem.setCart(null);
            cartItemRepository.deleteById(cartItem.getId());
        });
    }
}
