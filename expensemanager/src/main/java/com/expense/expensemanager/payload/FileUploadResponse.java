package com.expense.expensemanager.payload;

public class FileUploadResponse {
		private Boolean success;
		
		private String message;
		
		private String imageUrl;
		
		public FileUploadResponse(Boolean success , String message, String imageUrl) {
			this.success = success;
			this.message = message;
			this.imageUrl = imageUrl;
		}

		public Boolean getSuccess() {
			return success;
		}

		public void setSuccess(Boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		
		
}
