package com.ss.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;


public class ServerInfo {
    /**
     * Retrieve server information
     */
    private String computerName;
    private String userName;
    private String ip;
    private String userDomain;

    private String port; // 可以设置为你的应用程序使用的端口
    private String option; // 操作系统信息
    private String fileDirectory; // 本文件所在文件夹
    private String systemDirectory; // 系统所在文件夹
    private long scriptTimeout; // 服务器脚本超时时间
    private String language; // 语言种类
    private String frameworkVersion; // .NET Framework 版本
    private Date currentTime; // 当前时间
    private long uptime; // 上次启动到现在已运行时间（分钟）
    private String logicalDrives; // 逻辑驱动器
    private int cpuCount; // CPU 总数
    private String cpuType; // CPU 类型
    private long virtualMemory; // 虚拟内存
    private long currentMemoryUsage; // 当前程序占用内存
    private long aspNetMemoryUsage; // Asp.net所占内存
    private int currentSessionCount; // 当前Session数量
    private String currentSessionId; // 当前SessionID
    private String currentUser; // 当前系统用户名

    public ServerInfo() {
        try {
            this.computerName = InetAddress.getLocalHost().getHostName();
            this.ip = InetAddress.getLocalHost().getHostAddress();
            this.userName = System.getProperty("user.name");
            this.userDomain = System.getProperty("user.dir"); // 仅作为示例
            this.port = setPort();
            this.option = System.getProperty("os.name");
            this.fileDirectory = System.getProperty("user.dir"); // 具体路径请根据需要调整
            this.systemDirectory = System.getenv("SystemRoot") + "\\system32"; // 获取系统目录
            this.scriptTimeout = 30000; // 示例脚本超时时间
            this.language = Locale.getDefault().getDisplayLanguage();// 服务器语言
            this.frameworkVersion = "2.050727.3655"; // .NET Framework版本
            this.currentTime = new Date();
            this.uptime = setSystemUptime(); // 获取运行时间
            this.logicalDrives = setLogicalDrives(); // 获取逻辑驱动器
            this.cpuCount = Runtime.getRuntime().availableProcessors(); // 获取CPU总数
            this.cpuType = getCpuType(); // 自定义方法获取CPU类型
            this.virtualMemory = setVirtualMemory(); // 自定义方法获取虚拟内存
            this.currentMemoryUsage = setCurrentMemoryUsage(); // 自定义方法获取当前程序占用内存
            this.aspNetMemoryUsage = setAspNetMemoryUsage(); // 自定义方法获取Asp.net所占内存
            this.currentSessionCount = setCurrentSessionCount(); // 自定义方法获取当前Session数量
            this.currentSessionId = setCurrentSessionId(); // 自定义方法获取当前SessionID
            this.currentUser = System.getProperty("user.name"); // 当前系统用户名
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // setters
    private String setPort() {
        ConfigReader configReader = new ConfigReader("src/main/resources/application.properties");
        return configReader.getProperty("server.port");
    }

    private long setSystemUptime() {
        // TODO: 实现获取系统运行时间的逻辑
        return 7210; // 示例，返回7210分钟
    }

    private String setLogicalDrives() {
        // TODO: 实现获取逻辑驱动器的逻辑
        return "C:\\D:\\"; // 示例
    }

    private String setCpuType() {
        // TODO: 实现获取CPU类型的逻辑
        return "x86 Family 6 Model 42 Stepping 1, GenuineIntel"; // 示例
    }

    private long setVirtualMemory() {
        // TODO: 实现获取虚拟内存的逻辑
        return 52480; // 示例，单位MB
    }

    private long setCurrentMemoryUsage() {
        // TODO: 实现获取当前程序占用内存的逻辑
        return 3; // 示例，单位MB
    }

    private long setAspNetMemoryUsage() {
        // TODO: 实现获取Asp.net所占内存的逻辑
        return 51; // 示例，单位MB
    }

    private int setCurrentSessionCount() {
        // TODO: 实现获取当前Session数量的逻辑
        return 8; // 示例
    }

    private String setCurrentSessionId() {
        // TODO: 实现获取当前SessionID的逻辑
        return "gznhpwmp34004345jz2q3l45"; // 示例
    }


    // Getters
    public String getComputerName() {
        return computerName;
    }

    public String getUserName() {
        return userName;
    }

    public String getIp() {
        return ip;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public String getPort() {
        return port;
    }

    public String getOption() {
        return option;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public String getSystemDirectory() {
        return systemDirectory;
    }

    public long getScriptTimeout() {
        return scriptTimeout;
    }

    public String getLanguage() {
        return language;
    }

    public String getFrameworkVersion() {
        return frameworkVersion;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public long getUptime() {
        return uptime;
    }

    public String getLogicalDrives() {
        return logicalDrives;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public String getCpuType() {
        return cpuType;
    }

    public long getVirtualMemory() {
        return virtualMemory;
    }

    public long getCurrentMemoryUsage() {
        return currentMemoryUsage;
    }

    public long getAspNetMemoryUsage() {
        return aspNetMemoryUsage;
    }

    public int getCurrentSessionCount() {
        return currentSessionCount;
    }

    public String getCurrentSessionId() {
        return currentSessionId;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public class ConfigReader {
        private Properties properties = new Properties();

        public ConfigReader(String filePath) {
            try (InputStream inputStream = new FileInputStream(filePath)) {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getProperty(String key) {
            return properties.getProperty(key);
        }
    }
}

