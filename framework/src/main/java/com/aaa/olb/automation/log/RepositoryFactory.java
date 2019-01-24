package com.aaa.olb.automation.log;

public class RepositoryFactory {
	
	private static RepositoryFactory _instance;
	
	private ActionLogger logger;
	
	private RepositoryListener listener;
	
	static{
		_instance =new RepositoryFactory();
	}
	
	private RepositoryFactory(){
		logger = new ConsoleLogger();
		listener = new RepositoryListener() {
			
			@Override
			public void listen(Object sender) {
				// TODO Auto-generated method stub
			}
		};
	}
	
	public static RepositoryFactory getInstance(){
		return _instance;
	}

	public ActionLogger getLogger() {
		return logger;
	}

	public void setLogger(ActionLogger logger) {
		this.logger = logger;
	}

	public RepositoryListener getListener() {
		return listener;
	}

	public void setListener(RepositoryListener listener) {
		this.listener = listener;
	}
	
}
