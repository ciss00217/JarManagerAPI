package tw.com.jarmanager.api.socket;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;

import tw.com.jarmanager.api.consumer.ConsumerMessage;
import tw.com.jarmanager.api.util.Platform;

public class Test {
	private static final Logger logger = LogManager.getLogger(Test.class);
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
//		Runtime rt = Runtime.getRuntime();
//		Process process = rt.exec("java -jar D:\\JarManagerAPI.jar -startManager D:\\jarTest\\JarManagerAPI.xml");
//
//		Long Long = windowsProcessId(process);
//		
//		System.out.println("Long:"+Long);
		
//		List<Test> list=new ArrayList<Test>();
//		Test test = new Test();
//		test.setName("myName");
//		
//		list.add(test);
//	
//		
//		Test newTest=list.get(0);
//		
//		test.setName("change");
//		
//		System.out.println(newTest.getName());
//		
		
		Platform platfrom=Platform.detect();
		
		
		System.out.println(platfrom);
		
		
		

		
	}

	private static Long windowsProcessId(Process process) {
		if (process.getClass().getName().equals("java.lang.Win32Process")
				|| process.getClass().getName().equals("java.lang.ProcessImpl")) {
			/* determine the pid on windows plattforms */
			try {
				Field f = process.getClass().getDeclaredField("handle");
				f.setAccessible(true);
				long handl = f.getLong(process);

				Kernel32 kernel = Kernel32.INSTANCE;
				WinNT.HANDLE handle = new WinNT.HANDLE();
				handle.setPointer(Pointer.createConstant(handl));
				int ret = kernel.GetProcessId(handle);
				logger.debug("Detected pid: {}", ret);
				return Long.valueOf(ret);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
