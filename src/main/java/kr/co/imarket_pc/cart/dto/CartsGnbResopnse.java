package kr.co.imarket_pc.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartsGnbResopnse {
    private long productsCountInCart = 0;
    private long totalPrice = 0;
}
