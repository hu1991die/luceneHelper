package com.feizi.lucene.util;

import com.feizi.lucene.dao.manager.DBPool;

/**
 * 主要作用是返回class文件所在的文件目录或工程的根目录地址，这主要用于指定工程中
 * 配置文件的路径，不至于环境迁移而导致配置文件路径错误
 * @author ljj
 * @time 2015年11月3日 下午9:19:24
 * TODO
 */
public final class ClassUtils {

	/**
	 * 构造方法私有，防止实例化
	 */
	private ClassUtils(){
		
	}
	
	/**
	 * 返回类class文件所在的目录
	 * @param cls
	 * @return
	 */
	public static String getClassPath(Class<?> cls){
		return cls.getResource("").getPath().replaceAll("%20", " ");
	}
	
	/**
	 * 返回类class文件的地址
	 * @param cls
	 * @param hasName 是否显示文件名
	 * @return
	 */
	public static String getClassPath(Class<?> cls, boolean hasName){
		String name = cls.getSimpleName() + ".class";
		String path = cls.getResource(name).getPath().replaceAll("%20", " ");
		
		if(hasName){
			return path;
		}else{
			return path.substring(0, path.length() - name.length());
		}
	}
	
	/**
	 * 返回类class文件所在的顶级目录
	 * @param cls
	 * @return
	 */
	public static String getClassRootPath(Class<?> cls){
		return cls.getResource("/").getPath().replaceAll("%20", " ");
	}
	
	public static void main(String[] args) {
//		/Users/ljj/Documents/workspace/LuceneStudy/target/classes/com/feizi/lucene/util/
//		System.out.println(getClassPath(ClassUtils.class));
//		/Users/ljj/Documents/workspace/LuceneStudy/target/classes/com/feizi/lucene/util/ClassUtils.class
//		System.out.println(getClassPath(ClassUtils.class, true));
		
//		/Users/ljj/Documents/workspace/LuceneStudy/target/classes/
		System.out.println(getClassPath(DBPool.class));
		System.out.println(getClassPath(DBPool.class, true));
		System.out.println(getClassRootPath(DBPool.class));
	}
}
