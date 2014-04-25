package com.platform.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;

/** */
/**
 * 压缩/解压缩zip包处理类
 * 
 * @author yayagepei
 * @date 2008-8-25
 */
public class ZipUtil
{
    private int k = 1; // 定义递归次数变量

    public void zip(String zipFileName, File inputFile) throws Exception
    {
        System.out.println("压缩中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, inputFile, inputFile.getName(), bo);
        bo.close();
        out.close(); // 输出流关闭
        System.out.println("压缩完成");
    }

    public void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception
    { // 方法重载
        if (f.isDirectory())
        {
            File[] fl = f.listFiles();
            if (fl.length == 0)
            {
                out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
                System.out.println(base + "/");
            }
            for (int i = 0; i < fl.length; i++)
            {
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
            }
            System.out.println("第" + k + "次递归");
            k++;
        } else
        {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
            System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1)
            {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bi.close();
            in.close(); // 输入流关闭
        }
    }

    public void unzip(File file, String targetPath) throws IOException
    {
        OutputStream os = null;
        InputStream is = null;
        org.apache.tools.zip.ZipFile zipFile = null;
        try
        {
            zipFile = new org.apache.tools.zip.ZipFile(file);
            String directoryPath = "";
            if (StringUtils.isEmpty(targetPath))
            {
                throw new IOException();
            } else
            {
                directoryPath = targetPath;
            }
            Enumeration<?> entryEnum = zipFile.getEntries();
            if (null != entryEnum)
            {
                org.apache.tools.zip.ZipEntry zipEntry = null;
                while (entryEnum.hasMoreElements())
                {
                    zipEntry = (org.apache.tools.zip.ZipEntry) entryEnum.nextElement();
                    if (zipEntry.isDirectory())
                    {
                        directoryPath = targetPath + File.separator + zipEntry.getName();
                        System.out.println(directoryPath);
                        continue;
                    }
                    if (zipEntry.getSize() > 0)
                    {
                        // 文件
                        File targetFile = buildFile(targetPath + File.separator + zipEntry.getName(), false);
                        os = new BufferedOutputStream(new FileOutputStream(targetFile));
                        is = zipFile.getInputStream(zipEntry);
                        byte[] buffer = new byte[4096];
                        int readLen = 0;
                        while ((readLen = is.read(buffer, 0, 4096)) >= 0)
                        {
                            os.write(buffer, 0, readLen);
                        }
                        os.flush();
                        os.close();
                    } else
                    {
                        // 空目录
                        buildFile(targetPath + File.separator + zipEntry.getName(), true);
                    }
                }
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
            throw ex;
        } finally
        {
            if (null != zipFile)
            {
                zipFile = null;
            }
            if (null != is)
            {
                is.close();
            }
            if (null != os)
            {
                os.close();
            }
        }
    }

    public File buildFile(String fileName, boolean isDirectory)
    {
        File target = new File(fileName);
        if (isDirectory)
        {
            target.mkdirs();
        } else
        {
            if (!target.getParentFile().exists())
            {
                target.getParentFile().mkdirs();
                target = new File(target.getAbsolutePath());
            }
        }
        return target;
    }

    // public static void main(String[] args)
    // {
    // ZipUtil zipUtil = new ZipUtil();
    //
    // try
    // {
    // File f1 = new File("d:/tw");
    // zipUtil.zip("d:/myzip.zip", f1);
    //
    // File f2 = new File("d:/diguocms72.zip");
    // zipUtil.unzip(f2, "d:/tw");
    // } catch (Exception e)
    // {
    // e.printStackTrace();
    // }
    // }

}
