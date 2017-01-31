import com.alibaba.fastjson.JSON;
import com.cn.app.SampleApplication;
import com.cn.entity.Book;
import com.cn.entity.Brand;
import com.cn.entity.Car;
import com.cn.entity.UserInfo;
import com.cn.projection.StudentPro;
import com.cn.reposity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 1/25/2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class UserInfoTest {


    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    BrandDao brandDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CarDao carDao;


    @Test
    public void test() {
        List<UserInfo> list = userInfoDao.findAll();
        System.out.println(JSON.toJSON(list));
    }

    @Test
    public void saveTest() {
        UserInfo userInfo = new UserInfo();
        userInfo.setTel("17740867079");
        userInfo.setAddress("上海嘉定南翔");
        userInfo.setUserName("nurmemet");
        userInfoDao.save(userInfo);
        System.out.println("保存后返回Id" + userInfo.getId());
        userInfo.setUserName("test");
        userInfoDao.save(userInfo);
        List<UserInfo> list = userInfoDao.findAll();
        System.out.println(JSON.toJSON(list));

    }

    @Test
    public void findOne() {
        System.out.println(JSON.toJSON(userInfoDao.findOne(7L)));
    }

    @Test
    public void findByPropertyName() {
        //单个属性查询
        List<UserInfo> list = userInfoDao.findByUserName("test");
        System.out.println("根据属性名称查询");
        System.out.println(JSON.toJSON(list));
        //多个属性查询
        List<UserInfo> list2 = userInfoDao.findByUserNameAndAddress("test", "上海嘉定南翔");
        System.out.println("And查询");
        System.out.println(JSON.toJSON(list2));
        List<UserInfo> list3 = userInfoDao.findByUserNameAndAddressAndTel("test", "上海嘉定南翔", "17740867079");
        System.out.println("And查询 多个And");
        System.out.println(JSON.toJSON(list3));
        System.out.println("使用@Query查询");
        List<UserInfo> list4 = userInfoDao.queryByUserName("nurmemet");
        System.out.println(JSON.toJSON(list4));

        List<UserInfo> list5 = userInfoDao.findByAddressAndSort("上海嘉定南翔", new Sort("id"));
        System.out.println("使用Sort 排序");
        System.out.println(JSON.toJSON(list5));

        List<UserInfo> list6 = userInfoDao.findByNameAndAddress("nurmemet", "上海嘉定南翔");
        System.out.println("使用@Query+ @Param查询");
        System.out.println(JSON.toJSON(list6));

        List<UserInfo> list7 = userInfoDao.queryNative("nurmemet");
        System.out.println("使用@Query+ native sql");
        System.out.println(JSON.toJSON(list7));

        System.out.println("使用@Query+@Modifying");
        int count = userInfoDao.udpateByUserName("mustapa", "mustafa");
        System.out.println("本次操作影响的行数" + count);
    }


    @Test
    public void testBrand() {
        Brand brand = new Brand();
        brand.setBrandIntroduce("排气");
        brand.setBrandClassifyId(1L);
        brand.setBrandName("aibahe");
        brand.setState(1);
        brandDao.save(brand);

    }

    @Test
    public void ManyToOneTest() {
        List<UserInfo> list = userInfoDao.findAll();
        //用fastjson转换JSON输出
        //注意，不要用JSON.toJSON()方法输出，此方默认不支持循环引用检测，导致序列化的时候内存溢出
        System.out.println( JSON.toJSONString(list));
    }

    @Test
    public void EntityGraphTest(){
        Book book = bookDao.findByName("忏悔录");
        System.out.println(JSON.toJSONString(book));
    }

    @Test
    public void projectionTest(){
        StudentPro studentPro=studentDao.findById(1L);
        System.out.println(studentPro.getFullName());
        System.out.println(studentPro.getFirstName());
    }

    @Test
    public void storedProceduresTest(){
        int sum=carDao.anyFunctionName(10,20);
        System.out.println(sum);
        int sum1=carDao.procedure1(10,30);
        System.out.println(sum1);
        int sum2=carDao.anyFunctionName1(10,40);
        System.out.println(sum2);
        int sum3=carDao.anyFunctionName2(10,50);
        System.out.println(sum3);
    }


}
