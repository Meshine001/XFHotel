package com.xfhotel.hotel.support.wechat;

/**
 * Logger 消息
 * @author Ming
 *
 */
public class EnumMessage {

	public enum Status {
		warn, error, success
	}
	public enum Message {
		Log_0001(""), ;

		private String message;

		Message(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return this.message;
		}
	}

}
