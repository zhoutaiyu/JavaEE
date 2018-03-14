import com.alibaba.dubbo.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 周太宇 on 2018/3/14.
 */
public class Consumer {
    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        HelloService helloService = context.getBean(HelloService.class);
        System.out.println("consumer");
        System.out.println(helloService.say("zhou"));
    }
}
