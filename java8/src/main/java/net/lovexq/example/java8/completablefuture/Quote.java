package net.lovexq.example.java8.completablefuture;

/**
 * Quote
 *
 * @author LuPindong
 * @time 2019-07-03 21:57
 */
public class Quote {
    private final String shopName;

    private final Double price;

    private final Discount.Code discountCode;

    public Quote(String shopName, Double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        Double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public Double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}
