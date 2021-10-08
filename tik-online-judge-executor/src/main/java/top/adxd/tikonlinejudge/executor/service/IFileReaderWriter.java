package top.adxd.tikonlinejudge.executor.service;

public interface IFileReaderWriter {
    void writer(String fileFullPath,String content,Boolean append);
    String Reader(String fileFullPath);
}
