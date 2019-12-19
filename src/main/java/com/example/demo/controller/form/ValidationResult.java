package com.example.demo.controller.form;

/**
 * 表单校验结果, 用于收集Bean校验结果
 * @author semon
 *
 */
public class ValidationResult {
	private String errorMessage;
	
	public ValidationResult() {
	}
	
	public ValidationResult(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public boolean hasError() {
		return errorMessage != null && !errorMessage.trim().isEmpty();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
