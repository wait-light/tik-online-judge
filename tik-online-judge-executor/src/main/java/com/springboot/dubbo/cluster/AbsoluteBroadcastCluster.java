package com.springboot.dubbo.cluster;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;
import org.apache.dubbo.rpc.cluster.support.wrapper.AbstractCluster;

/**
 * 绝对广播
 * 不进行分组，每一个都执行，执行过程中发生错误直接忽略
 */
public class AbsoluteBroadcastCluster extends AbstractCluster {
    public static final String NAME = "absolute-broadcast";

    @Override
    protected <T> AbstractClusterInvoker<T> doJoin(Directory<T> directory) throws RpcException {
        return new AbsoluteBroadcastInvoker<T>(directory);
    }
}
