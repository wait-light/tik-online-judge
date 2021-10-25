package top.adxd.tikonlinejudge.executor.service.impl;

import cn.hutool.core.io.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.service.IFileReaderWriter;

import java.io.*;

@Service("fileReaderWriter")
public class FileReaderWriterImpl implements IFileReaderWriter {
    private static final Logger logger = LoggerFactory.getLogger(FileReaderWriterImpl.class);

    @Override
    public void writer(String fileFullPath, String content, Boolean append) {
        if (null == fileFullPath || "".equals(fileFullPath.trim()) || null == content) {
            return;
        }
        if (null == append) {
            append = false;
        }
        File file = new File(fileFullPath);
        if (!file.exists()) {
            try {
                file = FileUtil.touch(file);
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        if (!file.isFile()) {
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, append);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String Reader(String fileFullPath) {
        String result = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileFullPath);
            byte[] bytes = new byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            int len = -1;
            while (fileInputStream.available() > 0) {
                len = fileInputStream.read(bytes);
                stringBuilder.append(new String(bytes, 0, len));
            }
            result = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return result;
    }
}
