package controller;

import dao.BidDao;
import dao.UserDao;
import entity.Bid;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Array;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class likePersonController {
    @FXML private Button likeperson;

    //数据准备   可以拿到与自己相关的所有的信息包括和自己有关的所有的bid记录，主体的名字
    //在这个界面要显示我可能喜欢的人，通过下边的初始化函数
    public void init(String myname){
        //通过specialbidlist得到通过权值计算得到的该用户的标签
        //检验输出
        System.out.println("我的名字   "+myname);
        System.out.println("和我相关的所有交易信息    "+BidDao.specialbidlist.size());  //注意此时得到的是状态都为1的，即所有的bid都是对象只有一个人
        System.out.println("所有的交易信息    "+BidDao.bidlist.size());
        System.out.println("所有的用户信息    "+ UserDao.alluserlist.size());

        //首先遍历出该用户数量最多的成交的交易中交易最多的标签，作为该用户的信息
        ArrayList<String> all_possible_tag = new ArrayList<>();
        ArrayList<Integer> cnt_time = new ArrayList<Integer>();
        //首先筛选这个人的所有交易记录
        //先把第一个记录放入all_possible_tag中
        all_possible_tag.add((String) BidDao.specialbidlist.get(0).get("tag"));
        cnt_time.add(0);
//        System.out.println(BidDao.specialbidlist.get(0).get("tag")+"   "+BidDao.specialbidlist.get(1).get("tag"));

        System.out.println("-------");
        for (int i = 0;i < BidDao.specialbidlist.size();i++){
            System.out.println(BidDao.specialbidlist.get(i).get("tag"));
        }
        System.out.println("-------");
        for (int i = 0;i < BidDao.bidlist.size();i++){
            System.out.println(BidDao.bidlist.get(i).get("tag"));
        }
        System.out.println("-------");

        //首先先遍历获得有多少种类的tag
        for (int i = 1;i < BidDao.specialbidlist.size();i++){
            //遍历在所有的tag中是否有这个元素
            int flag = 1;
            String temtag = (String) BidDao.specialbidlist.get(i).get("tag");
            for (int j = 0;j < all_possible_tag.size();j++){
                System.out.println(temtag+"    "+all_possible_tag.get(j));
                //如果已经放进去了
                if (temtag.equals(all_possible_tag.get(j))){
//                    cnt_time.set(j,cnt_time.get(j)+1);
                    flag = 0;
                }
            }
            if (flag == 1){   //可能的tag中没有这个元素
                all_possible_tag.add(temtag);
                cnt_time.add(0);
            }
        }
        //然后再遍历一遍所有的tag
        for (int i = 0;i < all_possible_tag.size();i++){
            for (int j = 0;j < BidDao.specialbidlist.size();j++){
                if (all_possible_tag.get(i).equals(BidDao.specialbidlist.get(j).get("tag"))) {
                    cnt_time.set(i,cnt_time.get(i)+1);
                }
            }
        }
        //遍历所有的tag并输出相关的最多的tag   给这个用户(myname)定义一个flag
        int maxx = 0;
        String final_tag;
        for (int i = 0;i < all_possible_tag.size();i++){
            if (cnt_time.get(i) > maxx){
                maxx = cnt_time.get(i);
                final_tag = all_possible_tag.get(i);
            }
            System.out.println(all_possible_tag.get(i)+"  "+cnt_time.get(i));
        }
        //上面得到了myname这个用户对应的信息，然后现在要得到除了这个用户之外的其他所有用户
        //思路是先把除了myname 这个用户的其他所有人的对应的tag储存到一个arraylist里边

        //根据兴趣tag进行匹配
        ArrayList<User> tag_match = new ArrayList<>();
        ArrayList<Integer> cnt_match_tag = new ArrayList<>();
        //首先得到了myname这个人的所有tag
        Pattern p = Pattern.compile(":");
        String[] items = p.split(UserDao.userlist.get(0).getTag());
        //首先遍历列表alluserlist,将所有与该用户匹配的好友提取出来
        for (int i = 0;i < UserDao.alluserlist.size();i++){
            if (!UserDao.alluserlist.get(i).getName().equals(myname)){
                //然后把遍历到的第i个人的所有tag分割出来
                Pattern q = Pattern.compile(":");
                String[] i_items = p.split(UserDao.alluserlist.get(i).getTag());
                for (String part_of_mytag : items){
                    for (String part_of_itag : i_items){
                        if (part_of_itag.equals(part_of_mytag)){
                            //如果有匹配上的tag，就把这个人放进一个tag_match的arraylist
                            if (tag_match.size() == 0){
                                tag_match.add(UserDao.alluserlist.get(i));
                                cnt_match_tag.add(0);
                            }else {
                                //tag_match 中已经有元素，就要遍历一遍是否已经push进去
                                int flag = 1;
                                for (int j = 0;j < tag_match.size();j++){
                                    if (tag_match.get(j).equals(UserDao.alluserlist.get(i))){
                                        //遍历一遍tag_match 发现这个人已经在内部
                                        flag = 0;
                                    }
                                }
                                if (flag == 1){
                                    //遍历一遍发现tag_match中还没有这个人
                                    tag_match.add(UserDao.alluserlist.get(i));
                                    cnt_match_tag.add(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        //现在拿到了一个和myname 这个用户的name有tag重叠的用户的表单，然后计算每个和他重叠的tag的数量
        for (int i = 0;i < tag_match.size();i++){
            //首先在alluserlist中找到这个user
            for (int j = 0;j < UserDao.alluserlist.size();j++){
                if (UserDao.alluserlist.get(j).getName().equals(tag_match.get(i).getName())) {
                    //拿到这个人的标签
                    Pattern q = Pattern.compile(":");
                    String[] i_items = p.split(UserDao.alluserlist.get(j).getTag());
                    for (String part_of_mytag : items) {
                        for (String part_of_itag : i_items) {
                            if (part_of_itag.equals(part_of_mytag)) {
                                cnt_match_tag.set(i,cnt_match_tag.get(i)+1);
                            }
                        }
                    }
                }
            }
        }
        //检验输出和这个用户重叠的tag数
        for (int i = 0;i < tag_match.size();i++){
            System.out.println(tag_match.get(i).getName()+"  和该用户的tag相同的数量为    "+cnt_match_tag.get(i));
        }




        /***********************以上是tag之间的信息************************/
        //利用tag可以得到的信息是与自己相关的所有已经进行的项目中的各个项目的tag种类的分布的饼状图
        //得到自己交易中最多的tag
        //利用自己的兴趣勾选的tag和其他用户勾选的tag来进行匹配

        /***********************以下是用户之间的权值的赋予关系***********************/
        /*  首先拿到所有的state状态为1的成交记录
            然后拿到一个记录指向关系的二维矩阵，表示两者之间的指向权值的大小
            利用二维矩阵查看某两者之间的皮尔逊系数来表示两者的相关性

         */
        //首先拿到储存state为一的arraylist     和 储存所有用户的userlist    UserDao.alluserlist,然后 0 1 2 的索引代表的是alluserlist中的顺序
        ArrayList<Bid> all_valid_Bid = new ArrayList<>();
        for (int i = 0;i < BidDao.bidlist.size();i++){
            if ((Integer) BidDao.bidlist.get(i).get("state") == 1){
                Bid tembid = new Bid((String) BidDao.bidlist.get(i).get("callName"),(String) BidDao.bidlist.get(i).get("throwName"),"aa");
                all_valid_Bid.add(tembid);
            }
        }
        //通过遍历对二维矩阵进行赋值 利用map储存二维矩阵
//        Map<String, Map<String, Integer>> userPerfMap = new HashMap<String, Map<String, Integer>>();
        int[][] all_weight = new int[100][100];
        //然后遍历所有的bid
        //第一层遍历所有的bid信息
        for (int i = 0;i < all_valid_Bid.size();i++){
            //第二层遍历所有的用户，看看callname对上的，并把索引拿出来
            for (int j = 0;j < UserDao.alluserlist.size();j++){
                //第三层遍历所有的用户，看看throwname对上的，并把索引拿出来
                for(int k = 0;k < UserDao.alluserlist.size();k++){
                    System.out.println(UserDao.alluserlist.get(j).getName().equals((String)all_valid_Bid.get(i).getCallName())+"   "+UserDao.alluserlist.get(k).getName().equals((String)all_valid_Bid.get(i).getThrowName()));
                    if (UserDao.alluserlist.get(j).getName().equals((String)all_valid_Bid.get(i).getCallName()) && UserDao.alluserlist.get(k).getName().equals((String)all_valid_Bid.get(i).getThrowName())){
                        //拿到了指向关系的，是第j个user 给第k个user发出并成立了招标信息
                        //第k个user 主动向j提出了请求，指向关系赋的权值多,赋权值5
                        all_weight[k][j] += 5;
                        //反之 j是被动提出的，所以权值赋的少，赋2
                        all_weight[j][k] += 2;
                    }
                }
            }
        }
        //零的填充，防止出现套入算式的时候分母为零，也防止0在相乘的时候造成的值偏差过大    顺便
        System.out.println("输出weight 的权值数组");
        for (int i = 0;i < UserDao.alluserlist.size();i++){
            for (int j = 0;j < UserDao.alluserlist.size();j++){
                if (all_weight[i][j] == 0){
                    all_weight[i][j] = 1;
                }
                System.out.print(all_weight[i][j]+" ");
            }
            System.out.println();
        }


        //然后拿到了成交的weight权值数组
        //遍历一遍，找到myname在alluserlist中标记的位置
        int myname_pos = 0;
        for (int i = 0;i < UserDao.alluserlist.size();i++){
            if (myname.equals(UserDao.alluserlist.get(i).getName())){
                myname_pos = i;
            }
        }
        //然后再遍历alluserlist中的其他人
        //先开一个数组记录这个用户与其他人的皮尔逊系数
        double[] PCCs = new double[100];
        for (int i = 0;i < UserDao.alluserlist.size();i++) {
            if (!myname.equals(UserDao.alluserlist.get(i).getName())) {
                //此时拿到了myname的索引的位置以及此时的匹配的位置
                double pccs = 0;//最后的ans
                //Σxy=x1*y1+x2*y2+....xn*yn 乘积的和
                double multi = 0;
                // Σx=x1+x2+....xn  myname这个用户的和
                double my_total = 0;
                // Σy=y1+y2+....yn  i这个用户的和
                double i_total = 0;
                // Σx2=(x1)2+(x2)2+....(xn)2
                double pow_my_total = 0;
                // Σy2=(y1)2+(y2)2+....(yn)2
                double pow_i_total = 0;
                for (int j = 0; j < UserDao.alluserlist.size(); j++) {
                    multi += (all_weight[myname_pos][j] * all_weight[i][j]);
                    my_total += all_weight[myname_pos][j];
                    i_total += all_weight[i][j];
                    pow_my_total += Math.pow(all_weight[myname_pos][j],2);
                    pow_i_total += Math.pow(all_weight[i][j],2);
                }
                //套公式计算 p=(Σxy-Σx*Σy/n)/Math.sqrt((Σx2-(Σx)2/n)(Σy2-(Σy)2/n));
                //先计算分子
                double sd = multi - my_total * i_total / UserDao.alluserlist.size();
                //再计算分母
                double sm = Math.sqrt((pow_my_total - Math.pow(my_total, 2) / UserDao.alluserlist.size()) * (pow_i_total - Math.pow(i_total, 2) / UserDao.alluserlist.size()));
                //最后计算相关性系数
                System.out.println(multi+"  "+my_total+"  "+i_total+"  "+pow_my_total+"  "+pow_i_total+"  "+sd+"  "+sm);
                pccs = Math.abs(sm == 0 ? 1 : sd / sm);
                PCCs[i] = pccs;
            }
        }
        //然后推出了相关性系数并输出
        for (int i = 0;i < UserDao.alluserlist.size();i++){
            System.out.println(i+"  个user与myname 这个user 的系数 "+PCCs[i]);
        }

    }
    public void returnmainwindow(ActionEvent actionEvent) {
        //关掉当前界面
        Stage main = (Stage)likeperson.getScene().getWindow();
        main.close();
    }
}
