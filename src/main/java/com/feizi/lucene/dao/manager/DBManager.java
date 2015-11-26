package com.feizi.lucene.dao.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

/**
 * 数据库连接池管理类
 * @author ljj
 * @time 2015年11月6日 下午10:06:32
 * TODO
 */
public final class DBManager {
	
	private Logger log = Logger.getLogger(DBManager.class);

	private static DBManager dbManager = null;
	
	private DBManager(){
		try {
			log.info("数据库连接池的配置文件路径：" + DBPool.getDBPool().getPoolPath());
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			JAXPConfigurator.configure(DBPool.getDBPool().getPoolPath(), false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ProxoolException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接池管理对象
	 * @return
	 */
	protected static DBManager getDBManager(){
		if(null == dbManager){
			synchronized (DBManager.class) {
				if(null == dbManager){
					dbManager = new DBManager();
				}
			}
		}
		return dbManager;
	}
	
	/**
	 * 获取数据库链接
	 * @param dbPoolName
	 * @return
	 * @throws SQLException
	 */
	protected Connection getConnection(String dbPoolName) throws SQLException{
		return DriverManager.getConnection(dbPoolName);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new DBManager().getConnection("proxool.noveldb"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
