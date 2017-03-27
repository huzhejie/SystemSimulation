package View.Frame;


import Utils.DateChooser;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import twaver.TWaverUtil;
import twaver.table.TTable;
import twaver.table.TTableColumn;
import twaver.table.TTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2017/3/23.
 */
public class BlackoutNoticeFrame extends JFrame {
    private JPanel datePanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
    private String SPACE = "           ";
    private String space = " ";

    /**
     * 时间面板上的组件
     */
    private JLabel jLabel = new JLabel("停电通知");
    private JTextField dateField = new JTextField();
    private JLabel suffixLabel = new JLabel("#");
    private JButton output = new JButton("输出");
    private SimpleDateFormat format_ymd =  new SimpleDateFormat("yyyyMMdd    ");
    private SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd    ");


    /**
     * 中间主面板上的组件，按照从左到右从上到下的方式排列
     */
    private JLabel time = new JLabel("停电时间:");
    private DateChooser startPanel = new DateChooser();
    private JSpinner startSpinner = new JSpinner(
            new SpinnerDateModel(
                    new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0, 0).getTime(),
                    null, null, Calendar.SECOND ));
    private JLabel seperator = new JLabel("— —");
    JSpinner.DateEditor startDateEditor =new JSpinner.DateEditor(startSpinner, "HH:mm");

    private DateChooser endPanel = new DateChooser();
    private JSpinner endSpinner = new JSpinner(
            new SpinnerDateModel(
                    new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0, 0).getTime(),
                    null, null, Calendar.SECOND ));
    JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endSpinner,"HH:mm");

    private JLabel type = new JLabel("停电类型:");
    private JComboBox typeChooser = new JComboBox();//停电类型选择

    private JLabel companyName = new JLabel("厂商名称:");
    private JTextField name = new JTextField();

    private JLabel deadLine = new JLabel("停电线路:");
    private JTextField line = new JTextField();

    private JLabel cause = new JLabel("停电原因:");
    private JTextField deadCause = new JTextField();

    private JLabel deadZone  =new JLabel("停电区域:");
    private JTextField zone = new JTextField();

    private JLabel IdjLabel = new JLabel("公变编号:");
    private TTable table = new TTable();

    private JLabel userLabel = new JLabel("主要用户:");
    private TTable userTable = new TTable();

    private JLabel remarkLabel = new JLabel("备    注:");
    private JTextField remark = new JTextField();

    /**
     *底部面板上的组件，从右向左，从上到下的顺序
     *
     */
    private JLabel fromUser = new JLabel("发 送 人");
    private JTextField fromUserName = new JTextField();

    private JLabel toUser = new JLabel("接 收 人");
    private JTextField toUserName = new JTextField();

    private JLabel sendTime = new JLabel("发送时间:");
    private DateChooser send = new DateChooser();
    private JSpinner sendSpinner = new JSpinner(
            new SpinnerDateModel(
                    new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0, 0).getTime(),
                    null, null, Calendar.SECOND ));
    JSpinner.DateEditor sendDateEditor = new JSpinner.DateEditor(sendSpinner,"HH:mm");

    private JLabel recieveTime = new JLabel("接收时间:");
    private DateChooser recieve = new DateChooser();
    private JSpinner recieveSpinner = new JSpinner(
            new SpinnerDateModel(
                    new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0, 0).getTime(),
                    null, null, Calendar.SECOND ));
    JSpinner.DateEditor recieveDateEditor = new JSpinner.DateEditor(recieveSpinner,"HH:mm");

    private JLabel checkUser = new JLabel("审 核 人");
    private JTextField checkUserName = new JTextField();

    private JLabel checkTime = new JLabel("审核时间:");
    private DateChooser check = new DateChooser();
    private JSpinner checkSpinner = new JSpinner(
            new SpinnerDateModel(
                    new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0, 0).getTime(),
                    null, null, Calendar.SECOND ));
    JSpinner.DateEditor checkDateEditor = new JSpinner.DateEditor(checkSpinner,"HH:mm");

    private JLabel baseLabel = new JLabel("此 通 知 依 据 调 检 字:");
    private JTextField baseId = new JTextField();
    private JLabel suffix = new JLabel("#票");

    /**
     * 初始化窗口
     */
    public BlackoutNoticeFrame(){
        //总布局
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        this.setSize(500,800);
        this.setResizable(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(10,0,10,0);
        gc.gridheight = 2;
        gc.gridy = 0;
        this.add(datePanel,gc);
        gc.gridheight = 15;
        gc.gridy = 2;
        this.add(mainPanel,gc);
        gc.gridheight = 4;
        gc.gridy = 17;
        this.add(bottomPanel,gc);

        //顶部布局
        datePanel.setLayout(new GridBagLayout());
        GridBagConstraints g3 = new GridBagConstraints();
        Font font =new Font("宋体",Font.BOLD,20);
        jLabel.setFont(font);
        jLabel.setForeground(Color.red);
        dateField.setFont(font);
        dateField.setForeground(Color.red);
        dateField.setEditable(false);
        suffixLabel.setFont(font);
        suffixLabel.setForeground(Color.red);
        g3.fill = GridBagConstraints.BOTH;
        g3.insets = new Insets(0,20,0,0);
        g3.gridy = 0;
        g3.gridheight = 2;
        g3.gridx = 1;
        g3.gridwidth = 2;
        datePanel.add(jLabel,g3);
        g3.gridx = 3;
        datePanel.add(dateField,g3);
        g3.gridx = 5;
        g3.gridwidth = 1;
        datePanel.add(suffixLabel,g3);
        g3.gridx = 6;
        datePanel.add(output,g3);
//        System.out.println(format.format(new Date()));
        dateField.setText(format_ymd.format(new Date()));

        //中间的mainPanel布局
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints g1 = new GridBagConstraints();
        g1.fill = GridBagConstraints.BOTH;
        g1.anchor = GridBagConstraints.CENTER;
        g1.insets = new Insets(10,10,0,0);
        g1.gridheight = 1;
        g1.gridy = 0;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(time,g1);
        g1.gridx = 1;
        mainPanel.add(startPanel,g1);
        g1.gridx = 2;
        startSpinner.setEditor(startDateEditor);
        mainPanel.add(startSpinner,g1);
        g1.gridx = 3;
        mainPanel.add(seperator,g1);
        g1.gridx = 4;
        mainPanel.add(endPanel,g1);
        g1.gridx  =5;
        mainPanel.add(endSpinner,g1);
        endSpinner.setEditor(endDateEditor);

        g1.gridy = 1;
        g1.gridx = 0;
        mainPanel.add(type,g1);
        g1.gridx = 1;
        mainPanel.add(typeChooser,g1);
        typeChooser.addItem("计划");
        typeChooser.addItem("检修");
        typeChooser.addItem("故障");

        g1.gridy = 2;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(companyName,g1);
        g1.gridx = 1;
        g1.gridwidth = 5;
        mainPanel.add(name,g1);

        g1.gridy = 3;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(deadLine,g1);
        g1.gridx = 1;
        g1.gridwidth = 5;
        mainPanel.add(line,g1);

        g1.gridy = 4;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(cause,g1);
        g1.gridx = 1;
        g1.gridwidth = 5;
        mainPanel.add(deadCause,g1);

        g1.gridy = 5;
        g1.gridx = 0;
        g1.gridwidth = 1;
        mainPanel.add(deadZone,g1);
        g1.gridx = 1;
        g1.gridwidth = 5;
        mainPanel.add(zone,g1);

        g1.gridy = 6;
        g1.gridx = 0;
        mainPanel.add(IdjLabel,g1);
        g1.gridx = 2;
        mainPanel.add(userLabel,g1);

        g1.gridy = 7;
        g1.gridheight = 7;
        g1.gridx = 0;
        g1.gridwidth = 2;
        g1.ipady = 200;
        //隐藏表头右击菜单
        table.setTableHeaderPopupMenuFactory (null);
        //隐藏表体的右击菜单
        table.setTableBodyPopupMenuFactory(null);
        table.addColumn(new TTableColumn("id","序号",40));
        table.addColumn(new TTableColumn("description","公变描述",100));
        table.setColumnResizable (false);
        table.setRowResizable(false);
        TTableModel model = table.getTableModel();
        model.getColumnByName("id").setSortable(false);
        model.getColumnByName("description").setSortable(false);
        JScrollPane jScrollPane = new JScrollPane(table);
        mainPanel.add(jScrollPane,g1);

        g1.gridx = 2;
        g1.gridwidth = 6;
        g1.ipadx = 50;
        userTable.setTableBodyPopupMenuFactory(null);
        userTable.setTableHeaderPopupMenuFactory(null);
        userTable.addColumn(new TTableColumn("id","序号",40));
        userTable.addColumn(new TTableColumn("mainUser","主要用户",80));
        userTable.addColumn(new TTableColumn("contactor","联系人",60));
        userTable.addColumn(new TTableColumn("teleNo","联系方式",80));
        userTable.setColumnResizable(false);
        userTable.setRowResizable(false);
        userTable.setHeadAutoResizable(true);
        TTableModel tableModel = userTable.getTableModel();
        tableModel.getColumnByName("id").setSortable(false);
        tableModel.getColumnByName("mainUser").setSortable(false);
        tableModel.getColumnByName("contactor").setSortable(false);
        tableModel.getColumnByName("teleNo").setSortable(false);
        mainPanel.add(new JScrollPane(userTable),g1);

        g1.gridwidth = 1;
        g1.gridy = 14;
        g1.gridheight = 1;
        g1.gridx = 0;
        g1.ipady = 0;
        g1.ipadx = 0;
        mainPanel.add(remarkLabel,g1);
        g1.gridx = 1;
        g1.gridwidth = 5;
        mainPanel.add(remark,g1);

        /**
         * 底部面板的组件
         */
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        g2.fill = GridBagConstraints.BOTH;
        g2.anchor = GridBagConstraints.WEST;
        g2.insets = new Insets(10,5,0,0);
        g2.gridheight = 1;
        g2.gridy = 0;
        g2.gridx = 0;
        g2.gridwidth = 1;
        bottomPanel.add(fromUser,g2);
        g2.gridx = 1;
        bottomPanel.add(fromUserName,g2);
        g2.gridx = 3;
        bottomPanel.add(toUser,g2);
        g2.gridx = 4;
        bottomPanel.add(toUserName,g2);

        g2.gridy = 1;
        g2.gridx = 0;
        bottomPanel.add(sendTime,g2);
        g2.gridx = 1;
        bottomPanel.add(send,g2);
        g2.gridx = 2;
        sendSpinner.setEditor(sendDateEditor);
        bottomPanel.add(sendSpinner,g2);
        g2.gridx = 3;
        bottomPanel.add(recieveTime,g2);
        g2.gridx = 4;
        bottomPanel.add(recieve,g2);
        g2.gridx = 5;
        recieveSpinner.setEditor(recieveDateEditor);
        bottomPanel.add(recieveSpinner,g2);

        g2.gridy = 2;
        g2.gridx = 0;
        bottomPanel.add(checkUser,g2);
        g2.gridx = 1;
        bottomPanel.add(checkUserName,g2);
        g2.gridx = 3;
        bottomPanel.add(checkTime,g2);
        g2.gridx = 4;
        bottomPanel.add(check,g2);
        g2.gridx = 5;
        checkSpinner.setEditor(checkDateEditor);
        bottomPanel.add(checkSpinner,g2);

        g2.gridy = 3;
        g2.gridx = 0;
        g2.gridwidth = 2;
        bottomPanel.add(baseLabel,g2);
        g2.gridx = GridBagConstraints.RELATIVE;
        g2.gridwidth = 2;
        bottomPanel.add(baseId,g2);
        g2.gridx = GridBagConstraints.RELATIVE;
        bottomPanel.add(suffix,g2);

        /**
         * 输出按钮的监听器
         */
        output.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Map<String,Object> datamap = new HashMap();
//                datamap.put("noticeId",dateField.getText().trim());
//                datamap.put("startTime",sdf.parse(sdf.format(startPanel.getDate()))+" "+startSpinner.getValue().toString());
//                datamap.put("endTime",sdf.format(endPanel.getDate())+" "+endSpinner.getValue().toString());
//                datamap.put("type",typeChooser.getSelectedItem().toString().trim());
//                datamap.put("companyName",name.getText().trim());
//                datamap.put("deadLine",line.getText().trim());
//                datamap.put("deadCause",deadCause.getText().trim());
//                datamap.put("deadZone",zone.getText().trim());
//                datamap.put("remark",remark.getText().trim());
//                datamap.put("sender",fromUserName.getText().trim());
//                datamap.put("receiver",toUserName.getText().trim());
//                datamap.put("sendTime",sdf.parse(sdf.format(send.getDate()))+sendSpinner.getValue().toString());
//                datamap.put("receiveTime",sdf.format(recieve.getDate())+recieveSpinner.getValue().toString());
//                datamap.put("checker",checkUserName.getText().trim());
//                datamap.put("checkTime",sdf.parse(sdf.format(check.getDate()))+checkSpinner.getValue().toString());
//                datamap.put("baseId",baseId.getText().trim());
//
//                datamap.put("Id","1");
//                datamap.put("userName","2");
//                datamap.put("contactor","123");
//                datamap.put("teleNo","dadada");


                String filePath = "D:/停电通知"+dateField.getText().trim()+".docx";
                try {
                    OutputStream os = new FileOutputStream(filePath);
                    XWPFDocument doc = new XWPFDocument();
                    //创建标题
                    XWPFParagraph title = doc.createParagraph();
                    title.setAlignment(ParagraphAlignment.CENTER);
                    XWPFRun titleRun = title.createRun();
                    titleRun.setFontFamily("宋体");
                    titleRun.setFontSize(18);
                    titleRun.setText("停电通知 "+dateField.getText().trim()+"#");

                    //创建第一行
                    XWPFParagraph para1 = doc.createParagraph();
                    para1.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para1Run = para1.createRun();
                    para1Run.setFontFamily("仿宋");
                    para1Run.setFontSize(16);
                    para1Run.setText("尊敬的用户您好：");

                    //创建第二行
                    XWPFParagraph para2 = doc.createParagraph();
                    para2.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para2Run = para2.createRun();
                    para2Run.setFontFamily("仿宋");
                    para2Run.setFontSize(16);
                    para2Run.setText("停电时间："+sdf.format(startPanel.getDate())+" "+startSpinner.getValue().toString().substring(11,16)+" -- "+
                            sdf.format(endPanel.getDate())+" "+endSpinner.getValue().toString().substring(11,16));

                    //创建第三行
                    XWPFParagraph para3 = doc.createParagraph();
                    para3.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para3Run = para3.createRun();
                    para3Run.setFontFamily("仿宋");
                    para3Run.setFontSize(16);
                    para3Run.setText("停电类型："+typeChooser.getSelectedItem().toString().trim());

                    //创建第四行
                    XWPFParagraph para4 = doc.createParagraph();
                    para4.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para4Run = para4.createRun();
                    para4Run.setFontFamily("仿宋");
                    para4Run.setFontSize(16);
                    para4Run.setText("变电站名称："+name.getText().trim());

                    //创建第五行
                    XWPFParagraph para5 = doc.createParagraph();
                    para5.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para5Run = para5.createRun();
                    para5Run.setFontFamily("仿宋");
                    para5Run.setFontSize(16);
                    para5Run.setText("停电线路："+line.getText().trim());

                    //创建第六行
                    XWPFParagraph para6 = doc.createParagraph();
                    para6.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para6Run = para6.createRun();
                    para6Run.setFontFamily("仿宋");
                    para6Run.setFontSize(16);
                    para6Run.setText("停电原因："+deadCause.getText().trim());

                    //创建第七行
                    XWPFParagraph para7 = doc.createParagraph();
                    para7.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para7Run = para7.createRun();
                    para7Run.setFontFamily("仿宋");
                    para7Run.setFontSize(16);
                    para7Run.setText("停电区域及范围："+zone.getText().trim());

                    //创建第八行
                    XWPFParagraph para8 = doc.createParagraph();
                    para8.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para8Run = para8.createRun();
                    para8Run.setFontFamily("仿宋");
                    para8Run.setFontSize(16);
                    para8Run.setText("公变编号："+zone.getText().trim());

                    //创建第九行
                    XWPFParagraph para9 = doc.createParagraph();
                    para9.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para9Run = para9.createRun();
                    para9Run.setFontFamily("仿宋");
                    para9Run.setFontSize(16);
                    para9Run.setText("主要用户：一带及周边用户将受此次停电影响，请贵单位做好生产、生活安排。给您带来不便敬请谅解，谢谢！");

                    //创建第十行
                    XWPFParagraph para10 = doc.createParagraph();
                    para10.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para10Run = para10.createRun();
                    para10Run.setFontFamily("仿宋");
                    para10Run.setFontSize(16);
                    para10Run.setText("备注："+remark.getText().trim());

                    //创建第11行
                    XWPFParagraph para11 = doc.createParagraph();
                    para11.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para11Run = para11.createRun();
                    para11Run.setFontFamily("仿宋");
                    para11Run.setFontSize(16);
                    para11Run.setText("发送人："+fromUserName.getText().trim()+SPACE+"接收人："+toUserName.getText().trim());

                    //创建第12行
                    XWPFParagraph para12 = doc.createParagraph();
                    para12.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para12Run = para12.createRun();
                    para12Run.setFontFamily("仿宋");
                    para12Run.setFontSize(16);
                    para12Run.setText("发送时间："+sdf.format(send.getDate())+sendSpinner.getValue().toString().substring(11,16)+
                    space + "接收时间："+sdf.format(recieve.getDate())+recieveSpinner.getValue().toString().substring(11,16));

                    //创建第13行
                    XWPFParagraph para13 = doc.createParagraph();
                    para13.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para13Run = para13.createRun();
                    para13Run.setFontFamily("仿宋");
                    para13Run.setFontSize(16);
                    para13Run.setText("审核人："+checkUserName.getText().trim()+
                    SPACE+ "时  间："+sdf.format(check.getDate())+checkSpinner.getValue().toString().substring(11,16));

                    //创建第14行
                    XWPFParagraph para14 = doc.createParagraph();
                    para14.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para14Run = para14.createRun();
                    para14Run.setFontFamily("仿宋");
                    para14Run.setFontSize(16);
                    para14Run.setText("请配抢指挥中心提前7天发短信通知主要用户!");

                    //创建第15行
                    XWPFParagraph para15 = doc.createParagraph();
                    para15.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para15Run = para15.createRun();
                    para15Run.setFontFamily("仿宋");
                    para15Run.setFontSize(16);
                    para15Run.setText("此通知依据配调检字"+baseId.getText().trim()+"#票");

                    //创建第16行
                    XWPFParagraph para16 = doc.createParagraph();
                    para16.setAlignment(ParagraphAlignment.BOTH);
                    XWPFRun para16Run = para16.createRun();
                    para16Run.setFontFamily("仿宋");
                    para16Run.setFontSize(16);
                    para16Run.setText("附：主要用户列表及联系方式");

                    //创建表格
                    XWPFTable xwpfTable;
                    if(userTable.getRowCount()==0) {
                        xwpfTable = doc.createTable(2, userTable.getColumnCount());
                    }
                    else {
                        xwpfTable = doc.createTable(userTable.getRowCount()+1, userTable.getColumnCount());
                    }
                    //获取所有的行
                    List<XWPFTableRow> rows = xwpfTable.getRows();
                    //表格属性
                    CTTblPr tablePr = xwpfTable.getCTTbl().addNewTblPr();
                    //表格宽度
                    CTTblWidth width = tablePr.addNewTblW();
                    width.setW(BigInteger.valueOf(8000));
                    for(int i = 0;i<rows.size();i++){
                        rows.get(i).getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                        rows.get(i).getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2500));
                        rows.get(i).getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
                        rows.get(i).getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3000));
                        rows.get(i).getCell(0).getCTTc().addNewTcPr().addNewVAlign().setVal(STVerticalJc.CENTER);
                        rows.get(i).getCell(1).getCTTc().addNewTcPr().addNewVAlign().setVal(STVerticalJc.CENTER);
                        rows.get(i).getCell(2).getCTTc().addNewTcPr().addNewVAlign().setVal(STVerticalJc.CENTER);
                        rows.get(i).getCell(3).getCTTc().addNewTcPr().addNewVAlign().setVal(STVerticalJc.CENTER);
                        rows.get(i).setHeight(500);
                        if(i == 0){
                            rows.get(i).getCell(0).setText("序号");
                            rows.get(i).getCell(1).setText("用户名称");
                            rows.get(i).getCell(2).setText("联系人");
                            rows.get(i).getCell(3).setText("联系方式");
                        }
                        else
                        {
                            rows.get(i).getCell(0).setText("");
                            rows.get(i).getCell(1).setText("");
                            rows.get(i).getCell(2).setText("");
                            rows.get(i).getCell(3).setText("");
                        }
                    }


                    //创建文档
                    doc.write(os);
                    os.close();
                    doc.close();
                }catch (Exception e1){
                    e1.printStackTrace();
                }


            }
        });

    }
    public static void main(String[] args){
        BlackoutNoticeFrame frame = new BlackoutNoticeFrame();
        frame.setVisible(true);
        TWaverUtil.centerWindow(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /***
     * 写一个表格
     * @throws Exception
     */
    public void testWriteTable() throws Exception {
        XWPFDocument doc = new XWPFDocument();
        //创建一个5行5列的表格
        XWPFTable table = doc.createTable(5, 5);
        //这里增加的列原本初始化创建的那5行在通过getTableCells()方法获取时获取不到，但通过row新增的就可以。
//    table.addNewCol(); //给表格增加一列，变成6列
        table.createRow(); //给表格新增一行，变成6行
        List<XWPFTableRow> rows = table.getRows();
        //表格属性
        CTTblPr tablePr = table.getCTTbl().addNewTblPr();
        //表格宽度
        CTTblWidth width = tablePr.addNewTblW();
        width.setW(BigInteger.valueOf(8000));
        XWPFTableRow row;
        List<XWPFTableCell> cells;
        XWPFTableCell cell;
        int rowSize = rows.size();
        int cellSize;
        for (int i=0; i<rowSize; i++) {
            row = rows.get(i);
            //新增单元格
            row.addNewTableCell();
            //设置行的高度
            row.setHeight(500);
            //行属性
//       CTTrPr rowPr = row.getCtRow().addNewTrPr();
            //这种方式是可以获取到新增的cell的。
//       List<CTTc> list = row.getCtRow().getTcList();
            cells = row.getTableCells();
            cellSize = cells.size();
            for (int j=0; j<cellSize; j++) {
                cell = cells.get(j);
                if ((i+j)%2==0) {
                    //设置单元格的颜色
                    cell.setColor("ff0000"); //红色
                } else {
                    cell.setColor("0000ff"); //蓝色
                }
                //单元格属性
                CTTcPr cellPr = cell.getCTTc().addNewTcPr();
                cellPr.addNewVAlign().setVal(STVerticalJc.CENTER);
                if (j == 3) {
                    //设置宽度
                    cellPr.addNewTcW().setW(BigInteger.valueOf(3000));
                }
                cell.setText(i + ", " + j);
            }
        }
        //文件不存在时会自动创建
        OutputStream os = new FileOutputStream("D:\\table.docx");
        //写入文件
        doc.write(os);
        os.close();
    }

}
