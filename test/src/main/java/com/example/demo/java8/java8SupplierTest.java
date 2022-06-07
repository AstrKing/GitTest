package com.example.demo.java8;

import java.util.Arrays;
import java.util.List;

/**
 * @author WangYH
 * @date 2022/6/6
 */
public class java8SupplierTest {
    public static void main(String[] args) {
        final Car car = Car.create( Car::new );
        final List< Car > cars = Arrays.asList(car);
        Arrays.asList("1","2", "3").forEach(System.out::println);

        cars.forEach( Car::collide );

        cars.forEach( Car::repair );

        final Car police = Car.create( Car::new );
        cars.forEach( police::follow );
    }
}
