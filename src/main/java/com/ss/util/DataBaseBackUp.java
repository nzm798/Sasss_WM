package com.ss.util;

public class DataBaseBackUp {
    public static void backup(){
        try {
            Runtime rt=Runtime.getRuntime();
            // 调用 调用mysql的安装目录的命令
            Process child=rt.exec("D:\\Yun\\MySQL_8.0.31\\MySQL Server 8.0\\bin\\mysqldump -h localhost -u java -p admin sasss_wm");

        }
    }
}
