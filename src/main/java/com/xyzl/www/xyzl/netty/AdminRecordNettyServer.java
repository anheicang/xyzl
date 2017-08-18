package com.xyzl.www.xyzl.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Lucifer on 2017/2/15.
 */
public class AdminRecordNettyServer {

   public void bind(int port) throws Exception{
       EventLoopGroup bossGroup = new NioEventLoopGroup();
       EventLoopGroup workerGroup = new NioEventLoopGroup();

       try {
           //1 创建ServerBootStrap实例
           ServerBootstrap b = new ServerBootstrap();
           //2 绑定Reactor线程池,一个用于服务端接受客户端的连接,一个用于进行SocketChannel的网络读写
           b.group(bossGroup,workerGroup)
                   //3 设置并绑定服务端Channel.作为NIO服务端,需要创建ServerSocketChannel
                   .channel(NioServerSocketChannel.class)
                   //设置NioServerSocketChannel的TCP参数,此处将它的backlog设置为1024
                   .option(ChannelOption.SO_BACKLOG,1024)
                   //5 绑定I/O事件的处理类ChildChannelHandler
                   .childHandler(new ChildChannelHandler());

           System.out.println("服务器开启.");
           //绑定端口 启动
           b.bind(port).sync().channel().closeFuture().sync();
       } finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
       }
   }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
             socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

}


