package com.twjitm.core.start.http;

import com.twjitm.core.common.factory.thread.ThreadNameFactory;
import com.twjitm.core.start.AbstractNettyGameStartService;
import com.twjitm.core.utils.logs.LoggerUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

/**
 * @author EGLS0807 - [Created on 2018-08-16 15:07]
 * @company http://www.g2us.com/
 * @jdk java version "1.8.0_77"
 */
public class AbstractNettyGameStartHttpService extends AbstractNettyGameStartService {
    Logger logger = LoggerUtils.getLogger(AbstractNettyGameStartHttpService.class);
    String serverIp;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private ThreadNameFactory bossThreadNameFactory;
    private ThreadNameFactory workerThreadNameFactory;
    private ChannelInitializer channelInitializer;

    private ChannelFuture serverChannelFuture;

    private  boolean startFag=false;

    public AbstractNettyGameStartHttpService(int serverPort, String serverIp, String bossThreadName, String workThreadName, ChannelInitializer channelInitializer) {
        super(serverPort, new InetSocketAddress(serverIp,serverPort));
        this.serverIp=serverIp;
        this.bossThreadNameFactory = new ThreadNameFactory(bossThreadName);
        this.workerThreadNameFactory = new ThreadNameFactory(workThreadName);
        this.channelInitializer = channelInitializer;

    }

    @Override
    public void stopServer() {
        if(bossGroup != null){
            bossGroup.shutdownGracefully();
        }
        if(workerGroup != null){
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void startServer()  {
        bossGroup = new NioEventLoopGroup(1, bossThreadNameFactory);
        workerGroup = new NioEventLoopGroup(0, workerThreadNameFactory);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap = serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                //重用地址
                .childOption(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_RCVBUF, 65536)
                .childOption(ChannelOption.SO_SNDBUF, 65536)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // heap buf 's better
                .childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(false))
                .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(1000))
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(channelInitializer);
        try {
            serverChannelFuture = serverBootstrap.bind(serverPort).sync();
            serverChannelFuture.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
            startFag=true;
            logger.info("[---------------------HTTP SERVICE START IS SUCCESSFUL IP=[" + serverIp + "]LISTENER PORT NUMBER IS :[" + serverPort + "]------------]");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
