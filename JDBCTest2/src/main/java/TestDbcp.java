import edu.cn.util.MyDBCPDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * Created by 周太宇 on 2017/11/16.
 */
public class TestDbcp {
    public static void main(String[] args) {

        BasicDataSource dataSource = MyDBCPDataSource.DBCPDATASOURCE;
        System.out.println("当前数据库连接池的容量" + dataSource.getNumActive());
        Connection conn = null;
        PreparedStatement sm = null;
        ResultSet rs = null;
        try {
            /*conn = dataSource.getConnection();
            //编写sql，参数用?代替
            String sql = "SELECT title FROM bookta";
            //获得语句对象，承载sql语句
            sm = conn.prepareStatement(sql);
            //设置参数
            //pstmt.setString(1, type);
            //执行sql语句
            rs = sm.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                System.out.println("从数据库中得到一条记录的值:" + s);
            }*/
            //插入200组数据，查看速度
            long startTime = System.currentTimeMillis();    //获取开始时间
            conn = dataSource.getConnection();
            for(int i=2;i<202;i++) {
                String sql = "insert into bookta values(?,?,?,?)";
                sm = conn.prepareStatement(sql);
                //设置参数
                sm.setString(1, i+"");
                sm.setString(2, i+"");
                sm.setString(3, i+"");
                sm.setDouble(4, i);
                sm.executeUpdate();
            }
            long endTime = System.currentTimeMillis();    //获取结束时间
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(sm!=null)
                sm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("当前数据库连接池的容量" + dataSource.getNumActive());
                if(conn!=null)
                conn.close();
                System.out.println("当前数据库连接池的容量" + dataSource.getNumActive());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

