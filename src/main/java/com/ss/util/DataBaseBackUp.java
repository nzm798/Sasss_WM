package com.ss.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DataBaseBackUp {
    private static String backuppath;
    private static String restorepath;
    private static String logsavepath;

    static {
        try {
            Resource resource = new ClassPathResource("db.propertice");
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            properties.list(System.out);
            backuppath = properties.getProperty("backuppath");
            restorepath = properties.getProperty("restorepath");
            logsavepath = properties.getProperty("logsavepath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void backup() {
        try {
            Runtime rt = Runtime.getRuntime();
            // 调用 调用mysql的安装目录的命令
            Process child = rt.exec(backuppath);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream(); // 控制台的输出信息作为输入流
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
            InputStreamReader xx = new InputStreamReader(in, "utf-8");

            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            // 要用来做导入用的sql目标文件：
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
            FileOutputStream fout = new FileOutputStream(logsavepath + sdf.format(new Date()) + ".sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            ;
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 备份数据恢复
     */
    public static void restore(String fileName) {
        try {

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(restorepath);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(logsavepath + fileName), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            ;
            br.close();
            ;
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
