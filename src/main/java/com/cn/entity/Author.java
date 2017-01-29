package com.cn.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 1/28/2017.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    private Long id;
   private String name ;
    @OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
    private List<Book> bookList=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


}
