package com.aaa.olb.automation.log;

public class ActionRepository implements ActionLogger {

	private ActionLogger logger;

	private RepositoryListener listener;

	public ActionRepository() {
		this.logger = RepositoryFactory.getInstance().getLogger();
		this.listener = RepositoryFactory.getInstance().getListener();

		if (this.listener != null) {
			this.listener.listen(this);
		}
	}

	@Override
	public void info(Object sender, BaseAction action) {
		// TODO Auto-generated method stub
		if (this.logger != null) {
			this.logger.info(sender, action);
		}
	}

	@Override
	public void warn(Object sender, BaseAction action) {
		// TODO Auto-generated method stub
		if (this.logger != null) {
			this.logger.warn(sender, action);
		}
	}

	@Override
	public void warn(Object sender, BaseAction action, String warning) {
		// TODO Auto-generated method stub
		if (this.logger != null) {
			this.logger.warn(sender, action, warning);
		}
	}

	@Override
	public void error(Object sender, BaseAction action) {
		// TODO Auto-generated method stub
		if (this.logger != null) {
			this.logger.error(sender, action);
		}
	}

	@Override
	public void error(Object sender, BaseAction action, String message) {
		// TODO Auto-generated method stub
		if (this.logger != null) {
			this.logger.error(sender, action, message);
		}
	}

	@Override
	public void error(Object sender, BaseAction action, String message, Exception ex) {
		// TODO Auto-generated method stub
		if (this.logger != null) {
			this.logger.error(sender, action, message, ex);
		}
	}

}
