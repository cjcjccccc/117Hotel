import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 陈境聪
 * @date 2021-05-17 22:50
 */
public class Mytest {
    @Test
    public void test(){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }
}
