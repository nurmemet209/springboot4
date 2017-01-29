package com.cn.reposity;

import com.cn.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 1/28/2017.
 */
public interface BookDao extends CrudRepository<Book, Long> {
    /**
     * When the javax.persistence.loadgraph property is used to specify an entity graph, attributes that are specified
     * by attribute nodes of the entity graph are treated as FetchType.EAGER and attributes that are not specified are
     * treated according to their specified or default FetchType.
     *
     * @see JPA 2.1 Specification: 3.7.4.2 Load Graph Semantics
     * <p>
     *     在attributePaths里面声明的属性被视为FetchType.EAGER，其他属性被视为默认定义的加载模式
     * LOAD("javax.persistence.loadgraph"),
     * <p>
     * When the javax.persistence.fetchgraph property is used to specify an entity graph, attributes that are specified
     * by attribute nodes of the entity graph are treated as FetchType.EAGER and attributes that are not specified are
     * treated as FetchType.LAZY
     * @see JPA 2.1 Specification: 3.7.4.1 Fetch Graph Semantics
     *     在attributePaths里面声明的属性被视为FetchType.EAGER，其他属性被视为FetchType.LAZY
     * FETCH("javax.persistence.fetchgraph");
     */
    @EntityGraph(attributePaths = {"author.bookList"}, type = EntityGraph.EntityGraphType.FETCH)
    Book findByName(String name);

}
