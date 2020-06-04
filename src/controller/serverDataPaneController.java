package controller;

import dao.BidDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.HashMap;
import java.util.Map;

public class serverDataPaneController {
    //查询数据库将数据搞到map里面
    BidDao bidDao = new BidDao ();
    @FXML TabPane p1;
    @FXML TabPane p2;
    @FXML TabPane p3;
    @FXML Tab tab11;
    @FXML Tab tab12;
    @FXML Tab tab13;
    @FXML Tab tab21;
    @FXML Tab tab22;
    @FXML Tab tab23;
//    @FXML Tab tab31;
//    @FXML Tab tab32;
//    @FXML Tab tab33;

    @FXML
    public void showP1(ActionEvent event) {
        p1.setVisible(true);
        p2.setVisible(false);
//        p3.setVisible(false);
    }
    @FXML
    public void showP2(ActionEvent event) {
        p1.setVisible(false);
        p2.setVisible(true);
//        p3.setVisible(false);
    }
//    @FXML
//    public void showP3(ActionEvent event) {
//        p1.setVisible(false);
//        p2.setVisible(false);
//        p3.setVisible(true);
//    }
    public void init(){
        p1.setVisible(false);
        p2.setVisible(false);
//        p3.setVisible(false);
    }
    public void tagAndDealMoneyChart(){
        //为tab11设置饼状图
        Map<String, Float> data = bidDao.tagAndDealMoney ();
        //设置编辑器
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data ("知识产权",data.get("知识产权")),
                new PieChart.Data ("工商财税",data.get("工商财税")),
                new PieChart.Data ("科技服务",data.get("科技服务")),
                new PieChart.Data ("法律人事",data.get("法律人事")),
                new PieChart.Data ("印刷服务",data.get("印刷服务")),
                new PieChart.Data ("装修服务",data.get("装修服务")),
                new PieChart.Data ("翻译服务",data.get("翻译服务")),
                new PieChart.Data ("营销咨询",data.get("营销咨询")),
                new PieChart.Data ("金融服务",data.get("金融服务")),
                new PieChart.Data ("品牌设计",data.get("品牌设计")),
                new PieChart.Data ("影视动漫",data.get("影视动漫")),
                new PieChart.Data ("游戏",data.get("游戏")),
                new PieChart.Data ("IT/软件",data.get("IT/软件")),
                new PieChart.Data ("电商服务",data.get("电商服务")),
                new PieChart.Data ("工业设计",data.get("工业设计"))
        );
        //创建饼图,并将编辑器放到饼图上
        PieChart pieChart = new PieChart(pieChartData);
        //设置图表标题
        pieChart.setTitle("标签-成交额饼状统计图");
        //设置旋转方向
        pieChart.setClockwise(true);
        //设置标题长度
        pieChart.setLabelLineLength(50);
        //设置标题可见
        pieChart.setLabelsVisible(true);
        //设置起始角
        pieChart.setStartAngle(180);
        tab11.setContent (pieChart);
        //为tab12设置折线图
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("标签");
        LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("标签-成交额折线统计图");
        XYChart.Series series = new XYChart.Series();
        series.setName("成交额");
        series.getData ().add (new XYChart.Data("知识产权",(Number)data.get ("知识产权")));
        series.getData ().add (new XYChart.Data("工商财税",(Number)data.get ("工商财税")));
        series.getData ().add (new XYChart.Data("科技服务",(Number)data.get ("科技服务")));
        series.getData ().add (new XYChart.Data("法律人事",(Number)data.get ("知识产权")));
        series.getData ().add (new XYChart.Data("印刷服务",(Number)data.get ("印刷服务")));
        series.getData ().add (new XYChart.Data("装修服务",(Number)data.get ("装修服务")));
        series.getData ().add (new XYChart.Data("翻译服务",(Number)data.get ("翻译服务")));
        series.getData ().add (new XYChart.Data("营销咨询",(Number)data.get ("营销咨询")));
        series.getData ().add (new XYChart.Data("金融服务",(Number)data.get ("金融服务")));
        series.getData ().add (new XYChart.Data("品牌设计",(Number)data.get ("品牌设计")));
        series.getData ().add (new XYChart.Data("影视动漫",(Number)data.get ("影视动漫")));
        series.getData ().add (new XYChart.Data("游戏",(Number)data.get ("游戏")));
        series.getData ().add (new XYChart.Data("IT/软件",(Number)data.get ("IT/软件")));
        series.getData ().add (new XYChart.Data("电商服务",(Number)data.get ("电商服务")));
        series.getData ().add (new XYChart.Data("工业设计",(Number)data.get ("工业设计")));
        lineChart.getData ().add (series);
        tab12.setContent(lineChart);
        //为tab13设置条形图
        CategoryAxis xAxisOfBar = new CategoryAxis();
        NumberAxis yAxisOfBar = new NumberAxis();
        xAxis.setLabel("标签");
        BarChart<String,Number> barChart =
                new BarChart<> (xAxisOfBar,yAxisOfBar);

        lineChart.setTitle("标签-成交额条形统计图");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("成交额");
        series2.getData ().add (new XYChart.Data("知识产权",(Number)data.get ("知识产权")));
        series2.getData ().add (new XYChart.Data("工商财税",(Number)data.get ("工商财税")));
        series2.getData ().add (new XYChart.Data("科技服务",(Number)data.get ("科技服务")));
        series2.getData ().add (new XYChart.Data("法律人事",(Number)data.get ("知识产权")));
        series2.getData ().add (new XYChart.Data("印刷服务",(Number)data.get ("印刷服务")));
        series2.getData ().add (new XYChart.Data("装修服务",(Number)data.get ("装修服务")));
        series2.getData ().add (new XYChart.Data("翻译服务",(Number)data.get ("翻译服务")));
        series2.getData ().add (new XYChart.Data("营销咨询",(Number)data.get ("营销咨询")));
        series2.getData ().add (new XYChart.Data("金融服务",(Number)data.get ("金融服务")));
        series2.getData ().add (new XYChart.Data("品牌设计",(Number)data.get ("品牌设计")));
        series2.getData ().add (new XYChart.Data("影视动漫",(Number)data.get ("影视动漫")));
        series2.getData ().add (new XYChart.Data("游戏",(Number)data.get ("游戏")));
        series2.getData ().add (new XYChart.Data("IT/软件",(Number)data.get ("IT/软件")));
        series2.getData ().add (new XYChart.Data("电商服务",(Number)data.get ("电商服务")));
        series2.getData ().add (new XYChart.Data("工业设计",(Number)data.get ("工业设计")));
        barChart.getData ().add (series2);
        tab13.setContent(barChart);
    }
    public void tagAndNumChart(){
        //为tab21设置饼状图
        Map<String, Integer> data = bidDao.tagAndNum ();
        //设置编辑器
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data ("知识产权",data.get("知识产权")),
                new PieChart.Data ("工商财税",data.get("工商财税")),
                new PieChart.Data ("科技服务",data.get("科技服务")),
                new PieChart.Data ("法律人事",data.get("法律人事")),
                new PieChart.Data ("印刷服务",data.get("印刷服务")),
                new PieChart.Data ("装修服务",data.get("装修服务")),
                new PieChart.Data ("翻译服务",data.get("翻译服务")),
                new PieChart.Data ("营销咨询",data.get("营销咨询")),
                new PieChart.Data ("金融服务",data.get("金融服务")),
                new PieChart.Data ("品牌设计",data.get("品牌设计")),
                new PieChart.Data ("影视动漫",data.get("影视动漫")),
                new PieChart.Data ("游戏",data.get("游戏")),
                new PieChart.Data ("IT/软件",data.get("IT/软件")),
                new PieChart.Data ("电商服务",data.get("电商服务")),
                new PieChart.Data ("工业设计",data.get("工业设计"))
        );
        //创建饼图,并将编辑器放到饼图上
        PieChart pieChart = new PieChart(pieChartData);
        //设置图表标题
        pieChart.setTitle("标签-成交单量饼状统计图");
        //设置旋转方向
        pieChart.setClockwise(true);
        //设置标题长度
        pieChart.setLabelLineLength(50);
        //设置标题可见
        pieChart.setLabelsVisible(true);
        //设置起始角
        pieChart.setStartAngle(180);
        tab21.setContent (pieChart);
        //为tab22设置折线图
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("标签");
        LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("标签-成交单量折线统计图");
        XYChart.Series series = new XYChart.Series();
        series.setName("成交额");
        series.getData ().add (new XYChart.Data("知识产权",(Number)data.get ("知识产权")));
        series.getData ().add (new XYChart.Data("工商财税",(Number)data.get ("工商财税")));
        series.getData ().add (new XYChart.Data("科技服务",(Number)data.get ("科技服务")));
        series.getData ().add (new XYChart.Data("法律人事",(Number)data.get ("知识产权")));
        series.getData ().add (new XYChart.Data("印刷服务",(Number)data.get ("印刷服务")));
        series.getData ().add (new XYChart.Data("装修服务",(Number)data.get ("装修服务")));
        series.getData ().add (new XYChart.Data("翻译服务",(Number)data.get ("翻译服务")));
        series.getData ().add (new XYChart.Data("营销咨询",(Number)data.get ("营销咨询")));
        series.getData ().add (new XYChart.Data("金融服务",(Number)data.get ("金融服务")));
        series.getData ().add (new XYChart.Data("品牌设计",(Number)data.get ("品牌设计")));
        series.getData ().add (new XYChart.Data("影视动漫",(Number)data.get ("影视动漫")));
        series.getData ().add (new XYChart.Data("游戏",(Number)data.get ("游戏")));
        series.getData ().add (new XYChart.Data("IT/软件",(Number)data.get ("IT/软件")));
        series.getData ().add (new XYChart.Data("电商服务",(Number)data.get ("电商服务")));
        series.getData ().add (new XYChart.Data("工业设计",(Number)data.get ("工业设计")));
        lineChart.getData ().add (series);
        tab22.setContent(lineChart);
        //为tab23设置折线图
        CategoryAxis xAxisOfBar = new CategoryAxis();
        NumberAxis yAxisOfBar = new NumberAxis();
        xAxis.setLabel("标签");
        BarChart<String,Number> barChart =
                new BarChart<> (xAxisOfBar,yAxisOfBar);

        lineChart.setTitle("标签-成交单量条形统计图");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("成交额");
        series2.getData ().add (new XYChart.Data("知识产权",(Number)data.get ("知识产权")));
        series2.getData ().add (new XYChart.Data("工商财税",(Number)data.get ("工商财税")));
        series2.getData ().add (new XYChart.Data("科技服务",(Number)data.get ("科技服务")));
        series2.getData ().add (new XYChart.Data("法律人事",(Number)data.get ("知识产权")));
        series2.getData ().add (new XYChart.Data("印刷服务",(Number)data.get ("印刷服务")));
        series2.getData ().add (new XYChart.Data("装修服务",(Number)data.get ("装修服务")));
        series2.getData ().add (new XYChart.Data("翻译服务",(Number)data.get ("翻译服务")));
        series2.getData ().add (new XYChart.Data("营销咨询",(Number)data.get ("营销咨询")));
        series2.getData ().add (new XYChart.Data("金融服务",(Number)data.get ("金融服务")));
        series2.getData ().add (new XYChart.Data("品牌设计",(Number)data.get ("品牌设计")));
        series2.getData ().add (new XYChart.Data("影视动漫",(Number)data.get ("影视动漫")));
        series2.getData ().add (new XYChart.Data("游戏",(Number)data.get ("游戏")));
        series2.getData ().add (new XYChart.Data("IT/软件",(Number)data.get ("IT/软件")));
        series2.getData ().add (new XYChart.Data("电商服务",(Number)data.get ("电商服务")));
        series2.getData ().add (new XYChart.Data("工业设计",(Number)data.get ("工业设计")));
        barChart.getData ().add (series2);
        tab23.setContent(barChart);
    }
}
