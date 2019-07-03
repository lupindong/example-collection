package net.lovexq.example.java8.completablefuture;

/**
 * 商店
 *
 * @author LuPindong
 * @time 2019-07-03 20:29
 */
public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice(String product) {
        return this.calculatePrice(product);
    }

    public String getPriceString(String product) {
        Double price = this.calculatePrice(product);
        Discount.Code code = Discount.randomGetCode();
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private Double calculatePrice(String product) {
        Util.randomDelay();

        Double price = Util.RANDOM.nextDouble() * product.charAt(0);

        return price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shop{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
