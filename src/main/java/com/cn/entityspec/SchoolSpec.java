package com.cn.entityspec;

import com.cn.entity.School;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 1/31/2017.
 */
public class SchoolSpec {

    public static Specification<School> get() {
        return new Specification<School>() {
            public Predicate toPredicate(Root<School> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {

                String name="浙江理工";
                Double score=88D;
                Long id=1L;
                List<Predicate> predicates = new ArrayList<>();

                //               //like
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.name like ?
//                if(!StringUtils.isEmpty(name)){
//                    predicates.add(builder.like(root.get("name"),"%"+name+"%"));
//                }

//                //equal
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.id=1
//                if (id!=null){
//                    predicates.add(builder.equal(root.get("id"),id));
//                }

//                //greaterThan
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.score>88.0
//                if (score!=null){
//                    predicates.add(builder.greaterThan(root.get("score"),score));
//                }

//                //lessThan
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.score<88.0
//                if (score!=null){
//                    predicates.add(builder.lessThan(root.get("score"),score));
//                }

//                //between
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.score between 50 and 100
//                predicates.add(builder.between(root.get("score"),50,100));

//                //greaterThanOrEqualTo
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.score>=90.0
//                predicates.add(builder.greaterThanOrEqualTo(root.get("score"),90));

//                //lessThanOrEqualTo
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.score<=80.0
//                predicates.add(builder.lessThanOrEqualTo(root.get("score"),80));

//                //in
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.score in (90.0 , 88.0)
//                List<Double> scoreList=new ArrayList<>();
//                scoreList.add(90D);
//                scoreList.add(88D);
//                predicates.add(builder.in(root.get("score")).getExpression().in(scoreList));

//                //notLike
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.name not like ?
//                predicates.add(builder.notLike(root.get("name"),"%"+name+"%"));

//                //isFalse
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.is211=0
//                predicates.add(builder.isFalse(root.get("is211")));

//                //isTrue
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.is211=1
//                predicates.add(builder.isTrue(root.get("is211")));

//                //isNotNull
//                //select school0_.id as id1_4_, school0_.is211 as is2_4_, school0_.name as name3_4_, school0_.score as score4_4_ from school school0_ where school0_.name is not null
//                predicates.add(builder.isNotNull(root.get("name")));


                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
    public static Specification<School> getEmptyOne() {
        return new Specification<School>() {
            @Override
            public Predicate toPredicate(Root<School> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        };
    }

}
