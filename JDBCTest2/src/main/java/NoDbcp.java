import edu.cn.dao.BookDaoImpl;
import edu.cn.pojo.Book;

/**
 * Created by 周太宇 on 2017/11/16.
 */
public class NoDbcp {
    public static void main(String[] args) {
        BookDaoImpl bookDao = new BookDaoImpl();
        long startTime = System.currentTimeMillis();    //获取开始时间
        for (int i = 2; i < 202; i++) {
            Book book = new Book();
            book.setIsbn(i + "");
            book.setTitle(i + "");
            book.setType(i + "");
            book.setPrice(i);
            bookDao.insert(book);
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}
