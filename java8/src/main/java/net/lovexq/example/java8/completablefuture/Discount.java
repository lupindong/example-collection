package net.lovexq.example.java8.completablefuture;

/**
 * 折扣
 *
 * @author LuPindong
 * @time 2019-07-03 21:56
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static Code randomGetCode() {
        return Discount.Code.values()[Util.RANDOM.nextInt(Discount.Code.values().length)];
    }

    public static String applyDiscount(Quote quote) {
        return String.format("%s price is %.2f",
                quote.getShopName(), Discount.apply(quote.getPrice(), quote.getDiscountCode()));
    }

    private static Double apply(Double price, Code code) {

        Util.randomDelay();

        return Double.valueOf(price * (100 - code.percentage) / 100);
    }


}
