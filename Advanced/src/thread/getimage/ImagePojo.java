package thread.getimage;

public class ImagePojo {
	private ImageInfo[] result;

	private String code;

	private String message;

	public ImageInfo[] getResult() {
		return result;
	}

	public void setResult(ImageInfo[] result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ClassPojo [result = " + result + ", code = " + code + ", message = " + message + "]";
	}
}
