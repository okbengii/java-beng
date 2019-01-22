### 如何创建进程

	Java 中，可以通过两种方式创建进程，总共涉及 5 个类。

	1. 通过Runtime.exec()方法来创建一个进程

	2. 通过ProcessBuilder的start方法来创建进程


	Process类，Process类是一个抽象类，在它里面主要有几个抽象的方法，这个可以通过查看Process类的源代码得知：

　　位于java.lang.Process路径下：

```
public abstract class Process
{

    abstract public OutputStream getOutputStream();   //获取进程的输出流

    abstract public InputStream getInputStream();    //获取进程的输入流

    abstract public InputStream getErrorStream();   //获取进程的错误流

    abstract public int waitFor() throws InterruptedException;   //让进程等待

    abstract public int exitValue();   //获取进程的退出标志

    abstract public void destroy();   //摧毁进程
}

```


#### 通过ProcessBuilder创建进程

ProcessBuilder是一个final类，它有两个构造器：



```
public final class ProcessBuilder
{
    private List<String> command;
    private File directory;
    private Map<String,String> environment;
    private boolean redirectErrorStream;
 
    public ProcessBuilder(List<String> command) {
    if (command == null)
        throw new NullPointerException();
    this.command = command;
    }
 
    public ProcessBuilder(String... command) {
    this.command = new ArrayList<String>(command.length);
    for (String arg : command)
        this.command.add(arg);
    }
....
}
```


	构造器中传递的是需要创建的进程的命令参数，第一个构造器是将命令参数放进List当中传进去，第二构造器是以不定长字符串的形式传进去。

　　那么我们接着往下看，前面提到是通过ProcessBuilder的start方法来创建一个新进程的，我们看一下start方法中具体做了哪些事情。下面是start方法的具体实现源代码：


```
public Process start() throws IOException {
// Must convert to array first -- a malicious user-supplied
// list might try to circumvent the security check.
String[] cmdarray = command.toArray(new String[command.size()]);
for (String arg : cmdarray)
    if (arg == null)
    throw new NullPointerException();
// Throws IndexOutOfBoundsException if command is empty
String prog = cmdarray[0];
 
SecurityManager security = System.getSecurityManager();
if (security != null)
    security.checkExec(prog);
 
String dir = directory == null ? null : directory.toString();
 
try {
    return ProcessImpl.start(cmdarray,
                 environment,
                 dir,
                 redirectErrorStream);
} catch (IOException e) {
    // It's much easier for us to create a high-quality error
    // message than the low-level C code which found the problem.
    throw new IOException(
    "Cannot run program \"" + prog + "\""
    + (dir == null ? "" : " (in directory \"" + dir + "\")")
    + ": " + e.getMessage(),
    e);
}
}
```

 　　该方法返回一个Process对象，该方法的前面部分相当于是根据命令参数以及设置的工作目录进行一些参数设定，最重要的是try语句块里面的一句：

```
return ProcessImpl.start(cmdarray,
                    environment,
                    dir,
                    redirectErrorStream);
```


 　　说明真正创建进程的是这一句，注意调用的是ProcessImpl类的start方法，此处可以知道start必然是一个静态方法。那么ProcessImpl又是什么类呢？该类同样位于java.lang.ProcessImpl路径下，看一下该类的具体实现：

 　　ProcessImpl也是一个final类，它继承了Process类：

```
final class ProcessImpl extends Process {
 
    // System-dependent portion of ProcessBuilder.start()
    static Process start(String cmdarray[],
             java.util.Map<String,String> environment,
             String dir,
             boolean redirectErrorStream)
    throws IOException
    {
    String envblock = ProcessEnvironment.toEnvironmentBlock(environment);
    return new ProcessImpl(cmdarray, envblock, dir, redirectErrorStream);
    }
 ....
}
```

 　　这是ProcessImpl类的start方法的具体实现，而事实上start方法中是通过这句来创建一个ProcessImpl对象的：


```
return new ProcessImpl(cmdarray, envblock, dir, redirectErrorStream);
```


	而在ProcessImpl中对Process类中的几个抽象方法进行了具体实现。

　　说明事实上通过ProcessBuilder的start方法创建的是一个ProcessImpl对象。

　　下面看一下具体使用ProcessBuilder创建进程的例子，比如我要通过ProcessBuilder来启动一个进程打开cmd，并获取ip地址信息，那么可以这么写：


```
public class Test {
    public static void main(String[] args) throws IOException  {
        ProcessBuilder pb = new ProcessBuilder("cmd","/c","ipconfig/all");
        Process process = pb.start();
        Scanner scanner = new Scanner(process.getInputStream());

        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
```
	

	第一步是最关键的，就是将命令字符串传给ProcessBuilder的构造器，一般来说，是把字符串中的每个独立的命令作为一个单独的参数，不过也可以按照顺序放入List中传进去。	

　　至于其他很多具体的用法不在此进行赘述，比如通过ProcessBuilder的environment方法和directory(File directory)设置进程的环境变量以及工作目录等，感兴趣的朋友可以查看相关API文档。


#### 通过Runtime的exec方法来创建进程


	首先还是来看一下Runtime类和exec方法的具体实现，Runtime，顾名思义，即运行时，表示当前进程所在的虚拟机实例。

　　由于任何进程只会运行于一个虚拟机实例当中，所以在Runtime中采用了单例模式，即只会产生一个虚拟机实例：


```
public class Runtime {
    private static Runtime currentRuntime = new Runtime();

    /**
     * Returns the runtime object associated with the current Java application.
     * Most of the methods of class <code>Runtime</code> are instance
     * methods and must be invoked with respect to the current runtime object.
     *
     * @return  the <code>Runtime</code> object associated with the current
     *          Java application.
     */
    public static Runtime getRuntime() {
    return currentRuntime;
    }

    /** Don't let anyone else instantiate this class */
    private Runtime() {}
    ...
 }
```

 　　从这里可以看出，由于Runtime类的构造器是private的，所以只有通过getRuntime去获取Runtime的实例。接下来着重看一下exec方法 实现，在Runtime中有多个exec的不同重载实现，但真正最后执行的是这个版本的exec方法：


```
public Process exec(String[] cmdarray, String[] envp, File dir)
   throws IOException {
   return new ProcessBuilder(cmdarray)
       .environment(envp)
       .directory(dir)
       .start();
   }
```


 　　可以发现，事实上通过Runtime类的exec创建进程的话，最终还是通过ProcessBuilder类的start方法来创建的。

　　下面看一个例子，看一下通过Runtime的exec如何创建进程，还是前面的例子，调用cmd，获取ip地址信息：


```
public class Test {
    public static void main(String[] args) throws IOException  {
        String cmd = "cmd "+"/c "+"ipconfig/all";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream());
         
        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
```

 　　要注意的是，exec方法不支持不定长参数（ProcessBuilder是支持不定长参数的），所以必须先把命令参数拼接好再传进去。


