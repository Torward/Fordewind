import Model.UploadFileMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.RandomAccessFile;

@Slf4j
public class ByteInboundHandler extends ChannelInboundHandlerAdapter {
    private int readByte;
    private volatile int start = 0;
    private String filePath/* = "server/serverFiles"*/;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("Клиент подключён...");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.debug("Связь с Клиентом прекращена!");
        ctx.flush();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug(" Получен новый файл {}", msg);
        if (msg instanceof UploadFileMsg) {
            UploadFileMsg uf = (UploadFileMsg) msg;
            byte[] bytes = uf.getBytes();
            readByte = uf.getEnd();
            filePath = uf.getFilePath();
            String md5 = uf.getFile_md5();
            String path = filePath + File.separator + md5;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(start);
            randomAccessFile.write(bytes);
            start = start + readByte;
            if (readByte > 0) {
                ctx.writeAndFlush(start);
                randomAccessFile.close();
                if (readByte != 1024 * 8) {
                    Thread.sleep(1000);
                    channelInactive(ctx);
                }
            } else {
                ctx.flush();
                ctx.close();
                log.debug(readByte + " был прочитан!");
            }
        }

    }
}
