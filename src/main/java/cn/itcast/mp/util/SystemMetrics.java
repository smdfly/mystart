package cn.itcast.mp.util;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import org.hyperic.sigar.*;

public class SystemMetrics {
    public static void main(String[] args) throws ReflectionException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, MalformedObjectNameException, SigarException {
        // 获取操作系统管理接口
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        // 获取系统的 CPU 利用率
        double cpuLoad = osBean.getSystemLoadAverage();
        System.out.println("系统 CPU 利用率: " + cpuLoad);

        // 获取内存管理接口
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

        // 计算内存使用率
        long usedHeapMemory = heapMemoryUsage.getUsed();
        long maxHeapMemory = heapMemoryUsage.getMax();
        double heapMemoryUsagePercentage = (double) usedHeapMemory / maxHeapMemory * 100;

        long usedNonHeapMemory = nonHeapMemoryUsage.getUsed();
        long maxNonHeapMemory = nonHeapMemoryUsage.getMax();
        double nonHeapMemoryUsagePercentage = (double) usedNonHeapMemory / maxNonHeapMemory * 100;

        System.out.println("堆内存使用率: " + heapMemoryUsagePercentage + "%");
        System.out.println("非堆内存使用率: " + nonHeapMemoryUsagePercentage + "%");




        //OperatingSystemMXBean osBean1 = ManagementFactory.getOperatingSystemMXBean();
        ObjectName osName = ObjectName.getInstance(ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // 获取 CPU 使用率
        double cpuLoad1 = (double) mbs.getAttribute(osName, "SystemCpuLoad");
        System.out.println("CPU 使用率：" + cpuLoad1 * 100 + "%");

        // 获取内存使用率
        long totalMemory = (long) mbs.getAttribute(osName, "TotalPhysicalMemorySize");
        long freeMemory = (long) mbs.getAttribute(osName, "FreePhysicalMemorySize");
        double memoryUsage = (1 - (double) freeMemory / totalMemory) * 100;
        System.out.println("内存使用率：" + memoryUsage + "%");


        Sigar sigar = new Sigar();

        // 获取 CPU 使用率
        CpuPerc cpu = sigar.getCpuPerc();
        System.out.println("CPU 使用率：" + cpu.getCombined() * 100 + "%");

        // 获取内存使用率
        Mem mem = sigar.getMem();
        double memoryUsage1 = (1 - (double) mem.getFree() / mem.getTotal()) * 100;
        System.out.println("内存使用率：" + memoryUsage1 + "%");
    }
}
