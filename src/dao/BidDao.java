package dao;

import com.sun.xml.internal.rngom.parse.host.Base;
import entity.Bid;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.*;

public class BidDao {
    /*
    * crf
    * */
    //用来获得某个人的所有的招标信息的之第一部分
    public List<Bid> search_callname(String input){
        List<Bid> temlist = new ArrayList<Bid>();

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where callName like '%"+input+"%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bid tembid = new Bid();
                tembid.setNumOfBid(rs.getInt("numOfBid"));
                tembid.setTag(rs.getString("tag"));
                tembid.setPhoneNumber(rs.getString("phoneNumber"));
                tembid.setTopic(rs.getString("topic"));
                tembid.setContent(rs.getString("content"));
                tembid.setExpectMoney(rs.getString("expectMoney"));
                tembid.setTimeLimit(rs.getString("timeLimit"));
                tembid.setCallName(rs.getString("callName"));
                tembid.setThrowName(rs.getString("throwName"));
                temlist.add(tembid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temlist;
    }

    public List<Bid> search_throwname(String input){
        List<Bid> temlist = search_callname(input);

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where throwName like '%"+input+"%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bid tembid = new Bid();
                tembid.setNumOfBid(rs.getInt("numOfBid"));
                tembid.setTag(rs.getString("tag"));
                tembid.setPhoneNumber(rs.getString("phoneNumber"));
                tembid.setTopic(rs.getString("topic"));
                tembid.setContent(rs.getString("content"));
                tembid.setExpectMoney(rs.getString("expectMoney"));
                tembid.setTimeLimit(rs.getString("timeLimit"));
                tembid.setCallName(rs.getString("callName"));
                tembid.setThrowName(rs.getString("throwName"));
                temlist.add(tembid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temlist;
    }
    public List<Bid> search_tag(String input){
        List<Bid> temlist = search_throwname(input);

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where tag like '%"+input+"%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bid tembid = new Bid();
                tembid.setNumOfBid(rs.getInt("numOfBid"));
                tembid.setTag(rs.getString("tag"));
                tembid.setPhoneNumber(rs.getString("phoneNumber"));
                tembid.setTopic(rs.getString("topic"));
                tembid.setContent(rs.getString("content"));
                tembid.setExpectMoney(rs.getString("expectMoney"));
                tembid.setTimeLimit(rs.getString("timeLimit"));
                tembid.setCallName(rs.getString("callName"));
                tembid.setThrowName(rs.getString("throwName"));
                temlist.add(tembid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temlist;
    }

    public List<Bid> search_topic(String input){
        List<Bid> temlist = search_tag(input);

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where topic like '%"+input+"%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bid tembid = new Bid();
                tembid.setNumOfBid(rs.getInt("numOfBid"));
                tembid.setTag(rs.getString("tag"));
                tembid.setPhoneNumber(rs.getString("phoneNumber"));
                tembid.setTopic(rs.getString("topic"));
                tembid.setContent(rs.getString("content"));
                tembid.setExpectMoney(rs.getString("expectMoney"));
                tembid.setTimeLimit(rs.getString("timeLimit"));
                tembid.setCallName(rs.getString("callName"));
                tembid.setThrowName(rs.getString("throwName"));
                temlist.add(tembid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temlist;
    }

    public List<Bid> search_content(String input){
        List<Bid> temlist = search_topic(input);

        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where "+" content " +"like '%"+input+"%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Bid tembid = new Bid();
                tembid.setNumOfBid(rs.getInt("numOfBid"));
                tembid.setTag(rs.getString("tag"));
                tembid.setPhoneNumber(rs.getString("phoneNumber"));
                tembid.setTopic(rs.getString("topic"));
                tembid.setContent(rs.getString("content"));
                tembid.setExpectMoney(rs.getString("expectMoney"));
                tembid.setTimeLimit(rs.getString("timeLimit"));
                tembid.setCallName(rs.getString("callName"));
                tembid.setThrowName(rs.getString("throwName"));
                temlist.add(tembid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return temlist;
    }

    public List<Map<String ,Object>> getprespecialbidlist(String name){
        List<Map<String ,Object>> specialbidlist = new ArrayList<>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where callName like '%"+name+"%' and state like '1'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount ();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put (md.getColumnName (i), rs.getObject (i));
                }
                specialbidlist.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("第一次输出一下此时的size   "+specialbidlist.size());
        return specialbidlist;
    }

    //用来获得某个人的所有的招标信息的之第二部分
    public List<Map<String ,Object>> getspecialbidlist(String name){
        List<Map<String ,Object>> specialbidlist = getprespecialbidlist(name);
        System.out.println("第二次输出一下此时的size   "+specialbidlist.size());
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where throwName like '"+name+"' and state like '1'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount ();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put (md.getColumnName (i), rs.getObject (i));
                }
                specialbidlist.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("第三次输出一下此时的size   "+specialbidlist.size());
        return specialbidlist;
    }
    public List<Map<String ,Object>> getallbidlist(){
        List<Map<String ,Object>> specialbidlist = new ArrayList<>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from bidList where callName like '%'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount ();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put (md.getColumnName (i), rs.getObject (i));
                }
                specialbidlist.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("第一次输出一下此时的size   "+specialbidlist.size());
        return specialbidlist;
    }
    /*
     * crf
     * */



    /*
     * ywj
     * */
    //在BidDao中设置topic与callName的变量，用来在删除操作时调用
    public static String topic;
    public static String callName;
    //设置内容全局变量用来进行敏感字查询
    public static String content;
    public static List<Map<String ,Object>> bidlist;
    public static List<Map<String ,Object>> specialbidlist;

    //控制服务器的单个投标信息显示的界面
    public List serverInfoSoloShow(Bid bid) {
        //连接实例化
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //List用来存储ResultSet的值，解决连接关闭ResultSet无法查询的问题
        List<Map<String, Object>> list = new ArrayList<> ();
        try {
            //数据库语句嵌入,其中?的作用为模糊查询
            String sql = "select * from bidList where topic = ? and callName = ?";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement (sql);
            ps.setString (1, bid.getTopic ());
            ps.setString (2, bid.getCallName ());
            System.out.println (ps);
            //执行数据库语句
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount ();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next ()) {
                //初始化Map
                Map<String, Object> rowData = new HashMap<> ();
                //循环遍历这个条目的列并按照<列，值>的方法存储进Map中
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put (md.getColumnName (i), rs.getObject (i));
                }
                //将Map值存储到list中用于返回
                list.add (rowData);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll (conn, ps, rs);
        }

        return list;
    }

    //定义函数用来遍历招标表中的所有元素并在服务台的信息控制界面中整体显示
    public List serverInfoAllShow() {
        //连接实例化
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //List用来存储ResultSet的值，解决连接关闭ResultSet无法查询的问题
        List<Map<String, Object>> list = new ArrayList<> ();
        try {
            //数据库语句嵌入，整体查询
            String sql = "select * from bidList";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement (sql);
            //执行数据库语句
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount ();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next ()) {
                //初始化Map
                Map<String, Object> rowData = new HashMap<> ();
                //循环遍历这个条目的列并按照<列，值>的方法存储进Map中
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put (md.getColumnName (i), rs.getObject (i));
                }
                //将Map值存储到list中用于返回
                list.add (rowData);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll (conn, ps, rs);
        }
        return list;
    }

    public void deleteItem(Bid bid) {
        //连接实例化
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //结果集,在删除操作中无实际用处，用于方便整体的关闭
        ResultSet rs = null;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("警告");
        alert.setHeaderText("确定删除这条记录吗");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                //数据库语句嵌入,其中?的作用为方便封装
                String sql = "delete from bidList where topic = ? and callName = ?";
                //将数据库语句设置到预存储并将问号内容相对应
                ps = conn.prepareStatement (sql);
                ps.setString (1, bid.getTopic ());
                ps.setString (2, bid.getCallName ());
                System.out.println (ps);
                //执行数据库语句
                int count = ps.executeUpdate ();
                //设置弹出框提示是否删除成功的信息
                if (count > 0) {
                    Alert information = new Alert (Alert.AlertType.INFORMATION, "删除成功");
                    information.setTitle ("删除提示");
                    information.setHeaderText (bid.getCallName () + "所招标的主题为" + bid.getTopic () + "的招标信息");
                    Button infor = new Button("show Information");
                    information.showAndWait(); //显示弹窗，同时后续代码等挂起
                }
                else{
                    Alert information = new Alert (Alert.AlertType.INFORMATION, "删除失败");
                    information.setTitle ("删除提示");
                    information.setHeaderText (bid.getCallName () + "所招标的主题为" + bid.getTopic () + "的招标信息");
                    Button infor = new Button("show Information");
                    information.showAndWait(); //显示弹窗，同时后续代码等挂起
                }
            } catch (Exception e) {
                e.printStackTrace ();
            } finally {
                //关闭连接，使其他开发者可以使用
                BaseDao.closeAll (conn,ps,rs);
            }
        } else {}
    }

    //这个函数用来作为一个中介存储服务台显示信息界面的信息，用于删除时的实例调用
    public void setInfo(String topic,String callName){
        this.topic = topic;
        this.callName = callName;
    }

    //下面这些是用来统计数据的方法
    //这个函数用来反馈标签--成交量的数据
    public Map tagAndDealMoney(){
        //连接实例化
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //新的一个Map<String,float>用来存储各个标签的成交总额
        Map<String, Float> data = new HashMap<> ();
        try {
            //数据库语句嵌入,其中?的作用为模糊查询
            String sql = "select * from bidList";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement (sql);
            //执行数据库语句
            rs = ps.executeQuery ();
            //为每个tag都把标签设置上（超级傻的操作）
            data.put ("知识产权", (float) 0);
            data.put ("工商财税", (float) 0);
            data.put ("科技服务", (float) 0);
            data.put ("法律人事", (float) 0);
            data.put ("印刷服务", (float) 0);
            data.put ("装修服务", (float) 0);
            data.put ("翻译服务", (float) 0);
            data.put ("营销咨询", (float) 0);
            data.put ("金融服务", (float) 0);
            data.put ("品牌设计", (float) 0);
            data.put ("影视动漫", (float) 0);
            data.put ("游戏", (float) 0);
            data.put ("IT/软件", (float) 0);
            data.put ("电商服务", (float) 0);
            data.put ("工业设计", (float) 0);

            //循环遍历结果集，分别加到其结果上
            while (rs.next()){
                //取得该条信息的tag值
                String tagOfTemp = rs.getString ("tag");
                //判断exp的值是否可以进行float转换，可以的话进行转换，不可以的话进行异常处理
                try {
                    //取得tag对应的原有的成交额
                    float tempOfSolo = Float.valueOf (rs.getString ("expectMoney"));
                    float tempOfAll = Float.valueOf (data.get (tagOfTemp));
                    data.put(tagOfTemp,tempOfSolo+tempOfAll);
                }catch(NumberFormatException e){
                    e.printStackTrace ();
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll (conn, ps, rs);
        }
        //返回Map值
        return data;
    }

    public Map tagAndNum(){
        //连接实例化
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //新的一个Map<String,float>用来存储各个标签的成交总额
        Map<String, Integer> data = new HashMap<> ();
        try {
            //数据库语句嵌入,其中?的作用为模糊查询
            String sql = "select * from bidList";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement (sql);
            //执行数据库语句
            rs = ps.executeQuery ();
            //为每个tag都把标签设置上（超级傻的操作）
            data.put ("知识产权", 0);
            data.put ("工商财税", 0);
            data.put ("科技服务", 0);
            data.put ("法律人事", 0);
            data.put ("印刷服务", 0);
            data.put ("装修服务", 0);
            data.put ("翻译服务", 0);
            data.put ("营销咨询", 0);
            data.put ("金融服务", 0);
            data.put ("品牌设计", 0);
            data.put ("影视动漫", 0);
            data.put ("游戏", 0);
            data.put ("IT/软件", 0);
            data.put ("电商服务", 0);
            data.put ("工业设计", 0);

            //循环遍历结果集，分别加到其结果上
            while (rs.next()){
                //取得该条信息的tag值
                String tagOfTemp = rs.getString ("tag");
                //判断exp的值是否可以进行int转换，可以的话进行转换，不可以的话进行异常处理
                try {
                    //取得tag对应的原有的数量
                    int intTemp = Integer.valueOf (data.get (tagOfTemp));
                    data.put(tagOfTemp,intTemp+1);
                }catch(NumberFormatException e){
                    e.printStackTrace ();
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll (conn, ps, rs);
        }
        //返回Map值
        return data;
    }

    //用来查询确定tag中的招标信息
    public List selectTagContent(String tag) {
        //连接实例化
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //List用来存储ResultSet的值，解决连接关闭ResultSet无法查询的问题
        List<Map<String, Object>> list = new ArrayList<> ();
        try {
            //数据库语句嵌入，整体查询
            String sql = "select * from bidList where tag = ?";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement (sql);
            //设置问号中的语句
            ps.setString (1,tag);
            //执行数据库语句
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount ();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next ()) {
                //初始化Map
                Map<String, Object> rowData = new HashMap<> ();
                //循环遍历这个条目的列并按照<列，值>的方法存储进Map中
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put (md.getColumnName (i), rs.getObject (i));
                }
                //将Map值存储到list中用于返回
                list.add (rowData);
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll (conn, ps, rs);
        }
        return list;
    }

    public int numOfAllBidInSpecificTag(String tag){
        Connection conn = BaseDao.getconn ();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //记录条目个数的变量
        int count = 0;
        try {
            //数据库语句嵌入，整体查询
            String sql = "select * from bidList where tag = ?";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement (sql);
            ps.setString (1,tag);
            //执行数据库语句
            rs = ps.executeQuery ();
            ResultSetMetaData md = rs.getMetaData (); //获得结果集结构信息,元数据
            while (rs.next ()) count++;
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll (conn, ps, rs);
        }
        return count;
    }
    /*
     * ywj
     * */



    public int numOfAllBid() {
        Connection conn = BaseDao.getconn();
        int rowCount = 0;
        try {
            String sql = "select * from bidList";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rowCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(rowCount);
        return rowCount;
    }

    public int insertBid(Bid bid) {
        int flag = 0;
        Connection conn = BaseDao.getconn();
        try {
            String sql = "insert into bidList (phoneNumber,topic,content,expectMoney,timeLimit,callName,throwName,state,tag) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bid.getPhoneNumber());
            ps.setString(2, bid.getTopic());
            ps.setString(3, bid.getContent());
            ps.setString(4, bid.getExpectMoney());
            ps.setString(5, bid.getTimeLimit());
            ps.setString(6, bid.getCallName());
            ps.setString(7, bid.getThrowName());
            ps.setString(8, String.valueOf(bid.getState()));
            ps.setString(9, bid.getTag());
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //定义函数用来遍历招标表中的所有元素并在服务台的信息控制界面中整体显示
    public List BidDetailAllShow(String name) {
        //连接实例化
        Connection conn = BaseDao.getconn();
        //预存储类
        PreparedStatement ps = null;
        //查询之后的结果集
        ResultSet rs = null;
        //List用来存储ResultSet的值，解决连接关闭ResultSet无法查询的问题
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            //数据库语句嵌入，整体查询
            String sql = "select * from bidlist where callName = ?";
            //将数据库语句设置到预存储并将问号内容相对应
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            //执行数据库语句
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            //循环遍历结果集，将每个列的结果以Map的形式存储起来
            while (rs.next()) {
                //初始化Map
                Map<String, Object> rowData = new HashMap<>();
                //循环遍历这个条目的列并按照<列，值>的方法存储进Map中
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                //将Map值存储到list中用于返回
                list.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接，使其他开发者可以使用
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }

    //通过id查询单个广告信息
    public Bid SelectBid(int BidId) {
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        Bid bid = new Bid();
        ResultSet rs = null;
        try {
            String sql = "select * from bidList where numOfBid = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(BidId));
            rs = ps.executeQuery();
            while (rs.next()) {
                bid.setTag(rs.getString("tag"));
                bid.setPhoneNumber(rs.getString("phoneNumber"));
                bid.setTopic(rs.getString("topic"));
                bid.setContent(rs.getString("content"));
                bid.setExpectMoney(rs.getString("expectMoney"));
                bid.setTimeLimit(rs.getString("timeLimit"));
                bid.setCallName(rs.getString("callName"));
                bid.setThrowName(rs.getString("throwName"));
                bid.setState(rs.getInt("state"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return bid;
    }
    //在招标项目详情点击同意合伙后修改投标信息表的数据
    public void UpdateBidAfterAgr(String userName,int Bidid){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update bidList set throwName = '" + userName + "',state = 2 WHERE " + "numOfBid = " + Bidid;
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //在招标项目详情点击同意合伙后修改投标信息表的数据
    public void UpdateBidAfterSend(int Bidid,String userName){
        Bid bid = this.SelectBid(Bidid);
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        if(bid.getThrowName().split(":").length < 1){
            sql = "update bidList set throwName = '" + userName + "'WHERE " + "numOfBid = " + Bidid;
        }
        else {
            sql = "update bidList set throwName = '" + bid.getThrowName() + ":" + userName + "'WHERE " + "numOfBid = " + Bidid;
        }
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //在招标项目详情点击同意合伙后修改投标信息表的数据
    public void UpdateBidAfterCom(int Bidid){
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update bidList set state = 1 WHERE " + "numOfBid = " + Bidid;
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
