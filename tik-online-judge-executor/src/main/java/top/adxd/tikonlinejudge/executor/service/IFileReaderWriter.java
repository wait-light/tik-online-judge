package top.adxd.tikonlinejudge.executor.service;

public interface IFileReaderWriter {
    public void writer(String fileFullPath,String content,Boolean append);
    public String Reader(String fileFullPath);
}
