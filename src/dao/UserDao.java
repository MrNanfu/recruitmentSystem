package dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /*
    * crf
    * */
    public static List<User> alluserlist;
    public static List<User> userlist;

    //此函数用来获取数据库列表
    public List<User> getUserList() {
        List<User> userlist = new ArrayList<User>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from userList where password like '%%'";
            ps = conn.prepareStatement(sql);
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                User temuser = new User();
                temuser.setName(rs.getString("username"));
                temuser.setPwd(rs.getString("password"));
                temuser.setWork(rs.getString("work"));
                temuser.setPhoneNum(rs.getString("phoneNum"));
                temuser.setPlace(rs.getString("place"));
                temuser.setExpMoney(rs.getString("expMoney"));
                temuser.setTag(rs.getString("tagChoose"));
                temuser.setSystag(rs.getString("tagSystem"));
                temuser.setDealmoney(rs.getDouble("dealMoney"));
                userlist.add(temuser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    //用来对所有的User的信息进行修改
    public void changeInfo(List<User> specialuserlist){
        Connection conn = BaseDao.getconn();
        String sql = "update userList set username = '"+specialuserlist.get(0).getName()+"',password = '"+specialuserlist.get(0).getPwd()+"',work = '"+specialuserlist.get(0).getWork()+"',phoneNum = '"+specialuserlist.get(0).getPhoneNum()+"',place = '"+specialuserlist.get(0).getPlace()+"',expMoney = '"+specialuserlist.get(0).getExpMoney()+"',tagChoose = '"+specialuserlist.get(0).getTag()+"',tagSystem = '"+specialuserlist.get(0).getSystag()+"',dealMoney = '"+specialuserlist.get(0).getDealmoney()+"'"+" where username = '"+specialuserlist.get(0).getName()+"'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //用来查找特定的人，返回一个特定的人群所在的一个列表
    public List<User> getSpecialUserList(String name) {
        List<User> userlist = new ArrayList<User>();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            String sql = "select * from titles where title like '%三%'";
            //拼接的                拼接的title 是传进来的参数
            String sql = "select * from userList where username like '%"+name+"%'";
            ps = conn.prepareStatement(sql);
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                User temuser = new User();
                temuser.setName(rs.getString("username"));
                temuser.setPwd(rs.getString("password"));
                temuser.setWork(rs.getString("work"));
                temuser.setPhoneNum(rs.getString("phoneNum"));
                temuser.setPlace(rs.getString("place"));
                temuser.setExpMoney(rs.getString("expMoney"));
                temuser.setTag(rs.getString("tagChoose"));
                temuser.setSystag(rs.getString("tagSystem"));
                temuser.setDealmoney(rs.getDouble("dealMoney"));
                userlist.add(temuser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }
    public int insertUser(User user){
        int flag = 0;
        Connection conn = BaseDao.getconn();
        try {
            String sql = "insert into userList (username,password,work,phoneNum,place,expMoney,tagChoose,tagSystem,dealMoney) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPwd());
            ps.setString(3,user.getWork());
            ps.setString(4,user.getPhoneNum());
            ps.setString(5,user.getPlace());
            ps.setString(6,user.getExpMoney());
            ps.setString(7,user.getTag());
            ps.setString(8,user.getSystag());
            ps.setDouble(9,user.getDealmoney());
            flag = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public int selectUser(String Name){
        int flag = 0;
        Connection conn = BaseDao.getconn();
        try {
            String sql = "select * from userList where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,Name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                flag = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    //此函数用来返回user列表中是否有这个人
    public int selectAdminLogin(User user) {
        int count = 0;
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            count = 2;
            String sql = "select * from userlist where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
//            ps.setString(1,user.getNum());
            ps.setString(2, user.getPwd());
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return count;
    }
    //此函数通过username查询某个特定的用户
    public User getUser(String name){
        User temuser = new User();
        Connection conn = BaseDao.getconn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from userList where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            while (rs.next()) {
                temuser.setName(rs.getString("username"));
                temuser.setPwd(rs.getString("password"));
                temuser.setWork(rs.getString("work"));
                temuser.setPhoneNum(rs.getString("phoneNum"));
                temuser.setPlace(rs.getString("place"));
                temuser.setExpMoney(rs.getString("expMoney"));
                temuser.setTag(rs.getString("tagChoose"));
                temuser.setSystag(rs.getString("tagSystem"));
                temuser.setDealmoney(rs.getDouble("dealMoney"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temuser;
    }

}

