package me.kerol;
import java.util.Date;

import me.kerol.World;


public class Hello {
    public static final int H = 23;
    public static void hello(String[] args){
        Date a = new Date();
        System.out.println(a);
        System.out.println("hello ");
        World b = new World();
        b.print_world();
    }
}
