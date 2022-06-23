package com.codedecode.demo.entity.API;

 public class ErrorAPI {
		 private int code;
			public ErrorAPI(int code){
				this.code = code;
			}
			public int getCode() {
				return code;
			}
			public void setCode(int code) {
				this.code = code;
			}
}