import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.app.SampleApplication;
import com.cn.entity.UserInfo;
import com.cn.reposity.UserInfoDao;
import com.cn.service.UserInfoService;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 1/25/2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SampleApplication.class)
public class UserInfoTest {



    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void test(){
        List<UserInfo> list = userInfoDao.findAll();
        System.out.println(JSON.toJSON(list));
    }
    @Test
    public void saveTest(){
        UserInfo userInfo=new UserInfo();
        userInfo.setTel("17740867079");
        userInfo.setAddress("上海嘉定南翔");
        userInfo.setUserName("nurmemet");
        userInfoDao.save(userInfo);
        System.out.println("保存后返回Id"+userInfo.getId());
        userInfo.setUserName("test");
        userInfoDao.save(userInfo);
        List<UserInfo> list = userInfoDao.findAll();
        System.out.println(JSON.toJSON(list));

    }
    @Test
    public void findOne(){
        System.out.println(JSON.toJSON(userInfoDao.findOne(7L)));
    }
    @Test
    public void findByPropertyName(){
        //单个属性查询
        List<UserInfo> list = userInfoDao.findByUserName("test");
        System.out.println("根据属性名称查询");
        System.out.println(JSON.toJSON(list));
        //多个属性查询
        List<UserInfo> list2=userInfoDao.findByUserNameAndAddress("test","上海嘉定南翔");
        System.out.println("And查询");
        System.out.println(JSON.toJSON(list2));
        List<UserInfo> list3=userInfoDao.findByUserNameAndAddressAndTel("test","上海嘉定南翔","17740867079");
        System.out.println("And查询 多个And");
        System.out.println(JSON.toJSON(list3));
        System.out.println("使用@Query查询");
        List<UserInfo> list4=userInfoDao.queryByUserName("nurmemet");

        System.out.println(JSON.toJSON(list4));
    }


}
