package com.io;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;


public class ByteBufferTest
{

	public static void main(String[] args) throws Exception
	{
		//缓冲区分配和包装
		byte[] b1 = new byte[1024];
		ByteBuffer bb = ByteBuffer.wrap(b1);
		
		
		
		
		//缓冲区分片
		ByteBuffer buffer = ByteBuffer.allocate( 10 );
		//然后使用数据来填充这个缓冲区，在第 n 个槽中放入数字 n：
		for (int i=0; i<buffer.capacity(); ++i) {
		     buffer.put( (byte)i );
		}
		//现在我们对这个缓冲区 分片 ，以创建一个包含槽 3 到槽 6 的子缓冲区。在某种意义上，子缓冲区就像原来的缓冲区中的一个 窗口 。
		//窗口的起始和结束位置通过设置 position 和 limit 值来指定，然后调用 Buffer 的 slice() 方法：
		buffer.position( 3 );
		buffer.limit( 7 );
		ByteBuffer slice = buffer.slice();
		
		/*
		缓冲区份片和数据共享
		我们已经创建了原缓冲区的子缓冲区，并且我们知道缓冲区和子缓冲区共享同一个底层数据数组。让我们看看这意味着什么。
		我们遍历子缓冲区，将每一个元素乘以 11 来改变它。例如，5 会变成 55。
		*/
		
		for (int i=0; i<slice.capacity(); ++i) {
		     byte b = slice.get( i );
		     b *= 11;
		     slice.put( i, b );
		}
		
		//最后，再看一下原缓冲区中的内容：
		buffer.position( 0 );
		buffer.limit( buffer.capacity() );

		while (buffer.remaining()>0) {
		     System.out.println( buffer.get() );
		}
		
		/*
		缓冲区片对于促进抽象非常有帮助。可以编写自己的函数处理整个缓冲区，
		而且如果想要将这个过程应用于子缓冲区上，您只需取主缓冲区的一个片，并将它传递给您的函数。
		这比编写自己的函数来取额外的参数以指定要对缓冲区的哪一部分进行操作更容易。
		*/
		
		//只读缓冲区
		//不能将只读的缓冲区转换为可写的缓冲区。
		buffer.asReadOnlyBuffer();
		
		
		//将文件映射到内存
		//下面代码行将文件的前 1024 个字节映射到内存中：
		FileInputStream fin = null;
		FileChannel fc = fin.getChannel();;
		try
		{
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
			
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		//分散/聚集 I/O
		/*
		通道可以有选择地实现两个新的接口： ScatteringByteChannel 和 GatheringByteChannel。一个 ScatteringByteChannel 是一个具有两个附加读方法的通道：
		long read( ByteBuffer[] dsts );
		long read( ByteBuffer[] dsts, int offset, int length );
		
		分散/聚集的应用
		分散/聚集 I/O 对于将数据划分为几个部分很有用。例如，您可能在编写一个使用消息对象的网络应用程序，每一个消息被划分为固定长度的头部和固定长度的正文。您可以创建一个刚好可以容纳头部的缓冲区和另一个刚好可以容难正文的缓冲区。当您将它们放入一个数组中并使用分散读取来向它们读入消息时，头部和正文将整齐地划分到这两个缓冲区中。
		我们从缓冲区所得到的方便性对于缓冲区数组同样有效。因为每一个缓冲区都跟踪自己还可以接受多少数据，所以分散读取会自动找到有空间接受数据的第一个缓冲区。在这个缓冲区填满后，它就会移动到下一个缓冲区。
		
		聚集写入
		聚集写入 类似于分散读取，只不过是用来写入。它也有接受缓冲区数组的方法：
		long write( ByteBuffer[] srcs );
		long write( ByteBuffer[] srcs, int offset, int length );
		聚集写对于把一组单独的缓冲区中组成单个数据流很有用。为了与上面的消息例子保持一致，您可以使用聚集写入来自动将网络消息的各个部分组装为单个数据流，以便跨越网络传输消息。
		
		*/
		
		
		
		//文件锁定
		
		/*
		 * 您可以锁定整个文件或者文件的一部分。如果您获取一个排它锁，那么其他人就不能获得同一个
		 * 文件或者文件的一部分上的锁。如果您获得一个共享锁，那么其他人可以获得同一个文件或者文件
		 * 一部分上的共享锁，但是不能获得排它锁。文件锁定并不总是出于保护数据的目的。例如，您可能
		 * 临时锁定一个文件以保证特定的写操作成为原子的，而不会有其他程序的干扰。
		 * 
		 * 锁定文件
		要获取文件的一部分上的锁，您要调用一个打开的 FileChannel 上的 lock() 方法。注意，如果要获取一个排它锁，您必须以写方式打开文件。
		*/
		
		RandomAccessFile raf = new RandomAccessFile( "usefilelocks.txt", "rw" );
		FileChannel fc0 = raf.getChannel();
		FileLock lock = fc0.lock( 0, 0, false );
		
		//在拥有锁之后，您可以执行需要的任何敏感操作，然后再释放锁：
		lock.release();
		
		
		/*连网和异步 I/O
		//Selectors
		异步 I/O 中的核心对象名为 Selector。Selector 就是您注册对各种 I/O 事件的兴趣的地方，而且当那些事件发生时，就是这个对象告诉您所发生的事件。
		所以，我们需要做的第一件事就是创建一个 Selector：
		然后，我们将对不同的通道对象调用 register() 方法，以便注册我们对这些对象中发生的 I/O 事件的兴趣。register() 的第一个参数总是这个 Selector。
		*/
		
		Selector selector = Selector.open();

		/*
		打开一个 ServerSocketChannel
		为了接收连接，我们需要一个 ServerSocketChannel。事实上，我们要监听的每一个端口都需要有一个 ServerSocketChannel 。对于每一个端口，我们打开一个 ServerSocketChannel，如下所示：
		*/
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking( false );

		ServerSocket ss = ssc.socket();
		InetSocketAddress address = new InetSocketAddress( 2001 );
		ss.bind( address );
		
		//第一行创建一个新的 ServerSocketChannel ，最后三行将它绑定到给定的端口。第二行将 ServerSocketChannel 设置为 非阻塞的 。我们必须对每一个要使用的套接字通道调用这个方法，否则异步 I/O 就不能工作。
		
		//选择键
		//下一步是将新打开的 ServerSocketChannels 注册到 Selector上。为此我们使用 ServerSocketChannel.register() 方法，如下所示：
		SelectionKey key = ssc.register( selector, SelectionKey.OP_ACCEPT );

		//register() 的第一个参数总是这个 Selector。第二个参数是 OP_ACCEPT，这里它指定我们想要监听 accept 事件，也就是在新的连接建立时所发生的事件。这是适用于 ServerSocketChannel 的唯一事件类型。
		//请注意对 register() 的调用的返回值。 SelectionKey 代表这个通道在此 Selector 上的这个注册。当某个 Selector 通知您某个传入事件时，它是通过提供对应于该事件的 SelectionKey 来进行的。SelectionKey 还可以用于取消通道的注册。
		
		
		//内部循环
		//现在已经注册了我们对一些 I/O 事件的兴趣，下面将进入主循环。使用 Selectors 的几乎每个程序都像下面这样使用内部循环：
		int num = selector.select();

		Set selectedKeys = selector.selectedKeys();
		Iterator it = selectedKeys.iterator();

		while (it.hasNext()) {
		     SelectionKey key0 = (SelectionKey)it.next();
		     // ... deal with I/O event ...
		}
//		
//		监听新连接
//		程序执行到这里，我们仅注册了 ServerSocketChannel，并且仅注册它们“接收”事件。为确认这一点，我们对 SelectionKey 调用 readyOps() 方法，并检查发生了什么类型的事件：
//		if ((key.readyOps() & SelectionKey.OP_ACCEPT)
//		     == SelectionKey.OP_ACCEPT) {
//
//		     // Accept the new connection
//		     // ...
//		}
//
//		可以肯定地说， readOps() 方法告诉我们该事件是新的连接。
//		回页首
//		接受新的连接
//		因为我们知道这个服务器套接字上有一个传入连接在等待，所以可以安全地接受它；也就是说，不用担心 accept() 操作会阻塞：
//		ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
//		SocketChannel sc = ssc.accept();
//
//		下一步是将新连接的 SocketChannel 配置为非阻塞的。而且由于接受这个连接的目的是为了读取来自套接字的数据，所以我们还必须将 SocketChannel 注册到 Selector上，如下所示：
//		sc.configureBlocking( false );
//		SelectionKey newKey = sc.register( selector, SelectionKey.OP_READ );
//
//		注意我们使用 register() 的 OP_READ 参数，将 SocketChannel 注册用于 读取 而不是 接受 新连接。
//		回页首
//		删除处理过的 SelectionKey
//		在处理 SelectionKey 之后，我们几乎可以返回主循环了。但是我们必须首先将处理过的 SelectionKey 从选定的键集合中删除。如果我们没有删除处理过的键，那么它仍然会在主集合中以一个激活的键出现，这会导致我们尝试再次处理它。我们调用迭代器的 remove() 方法来删除处理过的 SelectionKey：
//		it.remove();
//
//		现在我们可以返回主循环并接受从一个套接字中传入的数据(或者一个传入的 I/O 事件)了。
//		回页首
//		传入的 I/O
//		当来自一个套接字的数据到达时，它会触发一个 I/O 事件。这会导致在主循环中调用 Selector.select()，并返回一个或者多个 I/O 事件。这一次， SelectionKey 将被标记为 OP_READ 事件，如下所示：
//		} else if ((key.readyOps() & SelectionKey.OP_READ)
//		     == SelectionKey.OP_READ) {
//		     // Read the data
//		     SocketChannel sc = (SocketChannel)key.channel();
//		     // ...
//		}
//
//		与以前一样，我们取得发生 I/O 事件的通道并处理它。在本例中，由于这是一个 echo server，我们只希望从套接字中读取数据并马上将它发送回去。关于这个过程的细节，请参见 参考资料 中的源代码 (MultiPortEcho.java)。
//		回页首
//		回到主循环
//		每次返回主循环，我们都要调用 select 的 Selector()方法，并取得一组 SelectionKey。每个键代表一个 I/O 事件。我们处理事件，从选定的键集中删除 SelectionKey，然后返回主循环的顶部。
//		这个程序有点过于简单，因为它的目的只是展示异步 I/O 所涉及的技术。在现实的应用程序中，您需要通过将通道从 Selector 中删除来处理关闭的通道。而且您可能要使用多个线程。这个程序可以仅使用一个线程，因为它只是一个演示，但是在现实场景中，创建一个线程池来负责 I/O 事件处理中的耗时部分会更有意义。
		
		
		
		
		
	}
	
}
