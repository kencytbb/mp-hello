package com.miracle.mp.miracle.client;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class BookService {

    public List<Book> all() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Agile Estimating and Planning", 99.9));
        books.add(new Book("Ancient Egyptian Magic", 21.3));
        books.add(new Book("The Power of Now", 55));
        books.add(new Book("Life is Strange", 7.9));
        books.add(new Book("IELTS Academic Essays Collection", 25));
        return books;
    }
    
}