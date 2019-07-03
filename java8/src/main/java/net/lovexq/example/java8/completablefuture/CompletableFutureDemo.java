package net.lovexq.example.java8.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * CompletableFuture Demo
 *
 * @author LuPindong
 * @time 2019-07-03 20:15
 */
public class CompletableFutureDemo {

    private static final List<Shop> shopList = Arrays.asList(new Shop("苹果"), new Shop("亚马逊"),
            new Shop("微软"), new Shop("谷歌"), new Shop("脸书"),
            new Shop("阿里巴巴"), new Shop("腾讯"), new Shop("三星"),
            new Shop("思科"), new Shop("英特尔"));

    private static final String product = "小霸王";

    private static final Executor EXECUTOR = Executors.newFixedThreadPool(Math.min(shopList.size(), 16),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            });

    /**
     * 最佳价格查询器：<br/>
     * 步骤1：传入商品名称查询原始价格
     * 步骤2：对原始价格进行解析
     * 步骤3：计算出折扣价格
     * 步骤4：转换汇率获得最终价格
     */
    public static void main(String[] args) {
        System.out.println("streamPriceFinder start...");
        long startTime = System.currentTimeMillis();
        System.out.println(streamPriceFinder(shopList, product));
        long endTime = System.currentTimeMillis();
        long retrievalTime = endTime - startTime;
        System.out.println("done in " + retrievalTime + " ms");
        System.out.println("streamPriceFinder end...\n");


        System.out.println("parallelStreamPriceFinder start...");
        long startTime2 = System.currentTimeMillis();
        System.out.println(parallelStreamPriceFinder(shopList, product));
        long endTime2 = System.currentTimeMillis();
        long retrievalTime2 = endTime2 - startTime2;
        System.out.println("done in " + retrievalTime2 + " ms");
        System.out.println("parallelStreamPriceFinder end...\n");


        System.out.println("completableFuturePriceFinder start...");
        long startTime3 = System.currentTimeMillis();
        System.out.println(completableFuturePriceFinder(shopList, product));
        long endTime3 = System.currentTimeMillis();
        long retrievalTime3 = endTime3 - startTime3;
        System.out.println("done in " + retrievalTime3 + " ms");
        System.out.println("completableFuturePriceFinder end...\n");


        System.out.println("completableFuturePriceFinderV2 start...");
        long startTime4 = System.currentTimeMillis();
        completableFuturePriceFinderV2(shopList, product, startTime4);
        long endTime4 = System.currentTimeMillis();
        long retrievalTime4 = endTime4 - startTime4;
        System.out.println("done in " + retrievalTime4 + " ms");
        System.out.println("completableFuturePriceFinderV2 end...\n");
    }

    /**
     * 同步价格查询器
     *
     * @param shopList
     * @param product
     */
    private static List<String> streamPriceFinder(List<Shop> shopList, String product) {
        return shopList.stream()
                .map(shop -> shop.getPriceString(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 并行流价格查询器
     *
     * @param shopList
     * @param product
     */
    private static List<String> parallelStreamPriceFinder(List<Shop> shopList, String product) {
        return shopList.parallelStream()
                .map(shop -> shop.getPriceString(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * CompletableFuture价格查询器
     *
     * @param shopList
     * @param product
     */
    private static List<String> completableFuturePriceFinder(List<Shop> shopList, String product) {
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceString(product), EXECUTOR))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)
                        )
                )
                .collect(Collectors.toList());

        return completableFutureList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * CompletableFuture价格查询器
     *  @param shopList
     * @param product
     * @param startTime
     */
    private static void completableFuturePriceFinderV2(List<Shop> shopList, String product, long startTime) {
        CompletableFuture[] completableFutureArray = shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceString(product), EXECUTOR))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)
                        )
                )
                .map(future -> future.thenAccept(
                        s -> System.out.println(s + " (done in " + (System.currentTimeMillis() - startTime) + " ms)"))
                )
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf((completableFutureArray)).join();
    }

}