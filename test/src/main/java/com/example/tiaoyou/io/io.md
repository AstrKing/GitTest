# IO

![image-20220608155205057](C:\Users\wangyh\AppData\Roaming\Typora\typora-user-images\image-20220608155205057.png)

==问题：不管文件读写还是网络发送接收，信息的最小存储单元都是字节，那么为啥还要分字节流和字符流呢？==

## 字节流

![image-20220608160650222](C:\Users\wangyh\AppData\Roaming\Typora\typora-user-images\image-20220608160650222.png)

## 字符流

![image-20220608160705729](C:\Users\wangyh\AppData\Roaming\Typora\typora-user-images\image-20220608160705729.png)

io有2个缺点：

1、多次内存复制，需要从源数据中读取数据流到缓冲区，然后再复制，再返回

2、阻塞，inputstream的read是一个while循环操作，它会一直等待数据读取，如果没有数据就绪，就一直等待

如何优化？采用nio

1、使用缓冲区优化，一个是buffer，一个是channel，buffer是一个连续的内存块，channel表示缓冲数据的源头或者目的地，它用于读取缓冲或者写入数据；io是面向流，而nio是面向buffer的，buffer可以一次性读完，然后后续再慢慢处理，io不行，不能中断

2、使用directbuffer，减少内存复制

3、避免阻塞，优化io操作